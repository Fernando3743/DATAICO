(def invoice (clojure.edn/read-string (slurp "src/invoice.edn")))

;; EXAMPLE
(def c 5)
(->> c (+ 3) (/ 2) (- 1))

(defn filterByTaxRate [{items :invoice/items}]
  (->> items (filter #(contains? % :taxable/taxes))
       (filter #(= (get (nth (get % :taxable/taxes) 0) :tax/rate) 19)))
  )


(defn filterByRetentionRate [{items :invoice/items}]
  (->> items (filter #(contains? % :retentionable/retentions))
       (filter #(= (get (nth (get % :retentionable/retentions) 0) :retention/rate) 1)))
  )

(defn SymmetricDifference  [s1 s2]
  (let [s1 (set s1) s2 (set s2)]
   (into (clojure.set/difference s1 s2) (clojure.set/difference s2 s1))))

(defn Main [invoice]
  (let [retention (filterByRetentionRate invoice) taxRate (filterByTaxRate invoice)]
    (SymmetricDifference taxRate retention)
    )
  )

(Main invoice)
