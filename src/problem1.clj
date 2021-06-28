(def invoice (clojure.edn/read-string (slurp "src/invoice.edn")))

;; EXAMPLE
(def c 5)
(->> c (+ 3) (/ 2) (- 1))


(defn searcher [{items :invoice/items}]
  (filter
    #(contains? % :taxable/taxes)
    items))


(searcher invoice)