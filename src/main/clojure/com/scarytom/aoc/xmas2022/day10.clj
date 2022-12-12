(ns com.scarytom.aoc.xmas2022.day10
  (:require
    [com.scarytom.aoc.utils :as utils]))

(defn process-instructions [lines]
  (reverse
    (reduce (fn [[x :as x-history] line]
              (let [[_ instruction value] (re-matches #"(noop|addx)(?: (-?\d+))?" line)]
                (case instruction
                  "noop" (conj x-history x)
                  "addx" (-> x-history
                             (conj x)
                             (conj (+ x (utils/to-int value)))))))
            '(1) lines)))

(defn calculate-signal-strength [lines]
  (let [register-values (process-instructions lines)]
    (->> (range 20 221 40)
         (map #(* % (nth register-values (dec %))))
         (reduce +))))

(defn render-screen [lines]
  (let [pixels (flatten (repeat 6 (range 0 40)))]
    (->> (process-instructions lines)
         (map (fn [pixel register-value]
                (if (<= (dec register-value) pixel (inc register-value)) "X" "."))
              pixels)
         (partition 40)
         (map #(apply str %)))))

(defn part-1 []
  (utils/read-input-file 2022 10 calculate-signal-strength))

(defn part-2 []
  (utils/read-input-file 2022 10 render-screen))