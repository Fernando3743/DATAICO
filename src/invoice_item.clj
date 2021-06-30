(ns invoice-item)
(use 'clojure.test)

(defn- discount-factor [{:invoice-item/keys [discount-rate]
                         :or                {discount-rate 0}}]
  (- 1 (/ discount-rate 100.0)))

(defn subtotal
  [{:invoice-item/keys [precise-quantity precise-price discount-rate]
    :as                item
    :or                {discount-rate 0}}]
  (* precise-price precise-quantity (discount-factor item)))


;; TESTS

(defn Item [id quantity price discount-rate]
  {
   :invoice-item/id                id
   :invoice-item/precise-quantity  quantity
   :invoice-item/precise-price     price
   :invoice-item/discount-rate     discount-rate
   })


(deftest normalItems
  (let [item1 (Item 1 5 29.99 5)
        item2 (Item 2 3 15.00 10)
        item3 (Item 3 7 10.99 50)]
    (is (= (subtotal item1) 142.4525))
    (is (= (subtotal item2) 40.5))
    (is (= (subtotal item3) 38.465))
    )
  )


(deftest noDiscountItems
  (let [item1 (Item 3 8 5.99 0)
        item2 (Item 4 1 50.99 0)
        item3 (Item 5 2 4.99 0)]
    (is (= (subtotal item1) 47.92))
    (is (= (subtotal item2) 50.99))
    (is (= (subtotal item3) 9.98))
    )
  )


(deftest samePriceAndAmountItems
  (let [item1 (Item 6 50 3.99 0)
        item2 item1
        item3 item1]
    (is (= (subtotal item1) (subtotal item2) (subtotal item3)))
    )
  )


(deftest samePriceAndAmountItems
  (let [item1 (Item 7 50 3.99 0)
        item2 item1
        item3 item1]
    (is (= (subtotal item1) (subtotal item2) (subtotal item3))))
  )


(deftest fullDiscount
    (let [item1 (Item 8 4 7.58 100)
          item2 (Item 9 6 90.99 100)
          item3 (Item 10 13 12.99 100)]
      (is (= (subtotal item1) (subtotal item2) (subtotal item3))))
      ; Should be 0
    )


(deftest ceroQuantityItems
  (let [item1 (Item 11 0 15.99 25)
        item2 (Item 12 0 20.99 33)
        item3 (Item 13 0 3.25 10)]
    (is (= (subtotal item1) (subtotal item2) (subtotal item3))))
    ; Should be 0
  )