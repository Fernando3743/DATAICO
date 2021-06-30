(in-ns 'invoice-spec)

(defn my-value-reader [key value]
  (if (re-find #"date" (str key))
    (.parse
      (java.text.SimpleDateFormat. "dd/MM/yyyy")
      value)
    value))

(defn my-key-reader [key]
  (if (re-find #"company_name" (str key))
    :name
    (keyword key)))

(def invoice (j/read-str (slurp "src/invoice.json")
                         :key-fn my-key-reader
                         :value-fn my-value-reader))

(identity invoice)
(s/explain ::invoice testing)