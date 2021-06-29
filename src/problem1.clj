(def invoice (clojure.edn/read-string (slurp "src/invoice.edn")))

;; EXAMPLE
(def c 5)
(->> c (+ 3) (/ 2) (- 1))

(defn filterByTaxRate [{items :invoice/items}]
  (->> items (filter #(contains? % :taxable/taxes))
       (filter #(= (get (nth (get % :taxable/taxes) 0) :tax/rate) 19)))
  )


(defn filterByRetentions [{items :invoice/items}]
  (->> items (filter #(contains? % :retentionable/retentions))
       (filter #(= (get (nth (get % :retentionable/retentions) 0) :retention/rate) 1)))
  )

(filterByRetentions invoice)
(filterByTaxRate invoice)
