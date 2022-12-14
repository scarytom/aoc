(ns com.scarytom.aoc.xmas2022.day11
  (:require
    [clojure.string :as string]
    [com.scarytom.aoc.utils :as utils]))

(defn read-monkey [[id starting-items operation test true-path false-path]]
  (let [test-denominator (utils/to-int (last (re-matches #"  Test: divisible by (\d+)" test)))]
    {:id               (utils/to-int (last (re-matches #"Monkey (\d+):" id)))
     :items            (mapv utils/to-int (string/split (subs starting-items 18) #", "))
     :operation        (let [[_ operator operand] (re-matches #"  Operation: new = old (.) (.*)" operation)]
                         (fn [old] ((eval (read-string operator)) old (if (= "old" operand) old (utils/to-int operand)))))
     :test-denominator test-denominator
     :test             (fn [x] (->> (if (zero? (rem x test-denominator)) true-path false-path)
                                    (re-matches #".* throw to monkey (\d+)")
                                    (last)
                                    (utils/to-int)))}))

(defn read-monkeys [lines]
  (->> lines
       (partition-by string/blank?)
       (take-nth 2)
       (mapv read-monkey)))

(defn do-round [worry-adjustment-fn monkeys]
  (loop [id 0
         monkeys monkeys]
    (if-let [{:keys [id items operation test]} (get monkeys id)]
      (recur (inc id)
             (-> (reduce (fn [monkeys item]
                           (let [new-item (worry-adjustment-fn (operation item))
                                 new-monkey-id (test new-item)]
                             (update-in monkeys [new-monkey-id :items] conj new-item)))
                         monkeys items)
                 (assoc-in [id :items] [])
                 (update-in [id :inspection-count] (fnil + 0) (count items))))
      monkeys)))

(defn monkey-business [worry-adjustment-fn rounds monkeys]
  (->> (nth (iterate (partial do-round worry-adjustment-fn) monkeys) rounds)
       (map :inspection-count)
       (sort >)
       (take 2)
       (apply *)))

(defn part-1
  ([]
   (utils/read-input-file 2022 11 (partial part-1 20)))
  ([rounds lines]
   (monkey-business #(quot % 3) rounds (read-monkeys lines))))

(defn part-2
  ([]
   (utils/read-input-file 2022 11 (partial part-2 10000)))
  ([rounds lines]
   (let [monkeys (read-monkeys lines)
         lcd (apply * (map :test-denominator monkeys))]
     (monkey-business #(rem % lcd) rounds monkeys))))