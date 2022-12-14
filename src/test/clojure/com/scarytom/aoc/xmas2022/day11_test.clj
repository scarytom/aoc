(ns com.scarytom.aoc.xmas2022.day11-test
  (:require
    [clojure.test :refer [deftest is]]
    [com.scarytom.aoc.xmas2022.day11 :as day11]))

(def sample-lines ["Monkey 0:"
                   "  Starting items: 79, 98"
                   "  Operation: new = old * 19"
                   "  Test: divisible by 23"
                   "    If true: throw to monkey 2"
                   "    If false: throw to monkey 3"
                   ""
                   "Monkey 1:"
                   "  Starting items: 54, 65, 75, 74"
                   "  Operation: new = old + 6"
                   "  Test: divisible by 19"
                   "    If true: throw to monkey 2"
                   "    If false: throw to monkey 0"
                   ""
                   "Monkey 2:"
                   "  Starting items: 79, 60, 97"
                   "  Operation: new = old * old"
                   "  Test: divisible by 13"
                   "    If true: throw to monkey 1"
                   "    If false: throw to monkey 3"
                   ""
                   "Monkey 3:"
                   "  Starting items: 74"
                   "  Operation: new = old + 3"
                   "  Test: divisible by 17"
                   "    If true: throw to monkey 0"
                   "    If false: throw to monkey 1"])

(deftest reads-input-data
  (is (= (mapv :items (day11/read-monkeys sample-lines))
         [[79, 98] [54, 65, 75, 74] [79, 60, 97] [74]])))

(deftest does-a-round
  (is (= (mapv :items (day11/do-round #(quot % 3) (day11/read-monkeys sample-lines)))
         [[20, 23, 27, 26] [2080, 25, 167, 207, 401, 1046] [] []])))

(deftest counts-part1-after-20-rounds
  (is (= (mapv :inspection-count (nth (iterate (partial day11/do-round #(quot % 3)) (day11/read-monkeys sample-lines)) 20))
         [101 95 7 105])))

(deftest counts-part2-after-20-rounds
  (is (= (mapv :inspection-count (nth (iterate (partial day11/do-round #(rem % (* 23 19 13 17))) (day11/read-monkeys sample-lines)) 20))
         [99 97 8 103])))

(deftest counts-part2-after-1000-rounds
  (is (= (mapv :inspection-count (nth (iterate (partial day11/do-round #(rem % (* 23 19 13 17))) (day11/read-monkeys sample-lines)) 1000))
         [5204 4792 199 5192])))

(deftest part-1-solution
  (is (= (day11/part-1) 100345)))

(deftest part-2-solution
  (is (= (day11/part-2) 28537348205)))