(in-ns 'invoice-spec)

(defn my-value-reader [key value]
  (if (re-find #"date" (str key))
    (.parse
      (java.text.SimpleDateFormat. "dd/MM/yyyy")
      value)
    value))

(def invoice (j/read-str (slurp "src/invoice.json")
                         :key-fn keyword
                         :value-fn my-value-reader))
(identity invoice)

(s/explain ::invoice invoice)