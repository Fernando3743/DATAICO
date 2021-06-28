(def invoice (clojure.edn/read-string (slurp "src/invoice.edn")))

;; EXAMPLE
(def c 5)
(->> c (+ 3) (/ 2) (- 1))



(defn filterTaxableTaxes [{items :invoice/items}]
  (filter #(contains? % :taxable/taxes)
  items))

(defn filterByRate [taxes]
  (filter #(= (get (nth (get % :taxable/taxes) 0) :tax/rate ) 19) taxes)
  )

(filterByRate (searcher invoice))