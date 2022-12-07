(ns com.scarytom.aoc.xmas2021.day03
  (:require [com.scarytom.aoc.utils :as utils]))

(defn readline [line]
  (map #(case %
          \1 1
          \0 -1)
       line))

(defn to-int [totals]
  (reduce-kv (fn [result idx total]
               (cond-> result (pos? total) (bit-set idx)))
             0 (vec (reverse totals))))

(defn power-consumption [totals]
  (let [gamma (to-int totals)
        epsilon (to-int (map unchecked-negate-int totals))]
    (* epsilon gamma)))

(defn part-1 []
  (utils/read-input-file 2021 3 #(->> %
                                      (map readline)
                                      (apply map +)
                                      (power-consumption))))

(defn find-line [lines pred]
  (loop [qualifying-lines lines
         idx 0]
    (let [qualifying-value (if (pred (reduce + (map #(nth % idx) qualifying-lines)))
                             1 -1)
          new-lines (filter #(= qualifying-value (nth % idx)) qualifying-lines)]
      (if (second new-lines)
        (recur new-lines (inc idx))
        (utils/only new-lines)))))

(defn part-2 []
  (utils/read-input-file 2021 3 (fn [lines]
                                  (let [lines (map readline lines)
                                        o2 (to-int (find-line lines #(>= % 0)))
                                        co2 (to-int (find-line lines #(< % 0)))]
                                    (* o2 co2)))))

