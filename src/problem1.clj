(def invoice (clojure.edn/read-string (slurp "src/invoice.edn")))

;; EXAMPLE
(def c 5)
(->> c (+ 3) (/ 2) (- 1))

(defn filterByTaxRate [{items :invoice/items}]
  (->> items (filter #(contains? % :taxable/taxes))
       (filter #(= (get (nth (get % :taxable/taxes) 0) :tax/rate ) 19)))
  )

(defn filterRetentions [{items :invoice/items}]
  (filter #(contains? % :retentionable/retentions)
          items))

(filterByTaxRate invoice)

(filterByTaxRate invoice)
