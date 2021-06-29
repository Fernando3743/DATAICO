(in-ns 'invoice-spec)

(defn my-value-reader [key value]
  (if (re-find #"_date" (str key))
    (.parse
      (java.text.SimpleDateFormat. "dd/MM/yyyy")
      value)
    value))

(j/read-str "{\"number\":42,\"date\":\"2012-06-02\"}"
               :value-fn my-value-reader
               :key-fn keyword)
;;=> {:number 42, :date #inst "2012-06-02T04:00:00.000-00:00"}

(def invoice (j/read-str (slurp "src/invoice.json")
                         :key-fn keyword
                         :value-fn my-value-reader))
(identity invoice)