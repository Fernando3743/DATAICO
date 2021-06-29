(def invoice (clojure.edn/read-string (slurp "src/invoice.edn")))

;; EXAMPLE
(def c 5)
(->> c (+ 3) (/ 2) (- 1))

(->> invoice (:invoice/items) (first) (:taxable/taxes) (some #(= (:tax/rate %) 19)) (boolean))
;; END EXAMPLES




(defn filterByTaxRate [{items :invoice/items}]
  (->> items (filter #(contains? % :taxable/taxes))
       (filter #(->> % (:taxable/taxes) (some (fn [%] (= (:tax/rate %) 19))) (boolean)))
  ))

(defn filterByRetentionRate [{items :invoice/items}]
  (->> items (filter #(contains? % :retentionable/retentions))
       (filter #(->> % (:retentionable/retentions) (some (fn [%] (= (:retention/rate %) 1))) (boolean)))
       ))

(defn SymmetricDifference  [s1 s2]
  (let [s1 (set s1) s2 (set s2)]
    (into (clojure.set/difference s1 s2) (clojure.set/difference s2 s1))))

(defn Main [invoice]
  (let [retention (filterByRetentionRate invoice) taxRate (filterByTaxRate invoice)]
    (SymmetricDifference taxRate retention)
    )
  )

(filterByTaxRate invoice)

(Main invoice)







