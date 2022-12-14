(ns com.scarytom.aoc.xmas2022.day13
  (:require
    [clojure.string :as string]
    [com.scarytom.aoc.utils :as utils]))

(defn to-vec [x]
  (if (vector? x) x [x]))

(defn compare-packets [a b]
  (if (and (int? a) (int? b))
    (compare a b)
    (let [a' (to-vec a)
          b' (to-vec b)]
      (or (some->> (map compare-packets a' b') (remove zero?) (first))
          (compare (count a') (count b'))))))

(defn read-packets [lines]
  (->> lines
       (remove string/blank?)
       (mapv (comp eval read-string))))

(defn ordered-pair-numbers [lines]
  (->> lines
       read-packets
       (partition 2)
       (map-indexed (fn [idx [a b]]
                      (when-not (pos? (compare-packets a b))
                        (inc idx))))
       (remove nil?)))

(defn decoder-key [lines]
  (let [divider-packets #{[[2]] [[6]]}]
    (->> lines
         read-packets
         (concat divider-packets)
         (sort-by identity compare-packets)
         (map-indexed (fn [idx packet] (when (divider-packets packet) (inc idx))))
         (remove nil?)
         (apply *))))

(defn part-1 []
  (utils/read-input-file 2022 13 #(reduce + (ordered-pair-numbers %))))

(defn part-2 []
  (utils/read-input-file 2022 13 decoder-key))