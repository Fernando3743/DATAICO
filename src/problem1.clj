(def invoice (clojure.edn/read-string (slurp "src/invoice.edn")))

;; EXAMPLE
(def c 5)
(->> c (+ 3) (/ 2) (- 1))


(defn searcher [{items :invoice/items}]
  (doseq [single-item items]
    (doseq [tax (->> single-item (:taxable/taxes))]
      (->> tax (:tax/rate) (< 19))
    )))


(searcher invoice)