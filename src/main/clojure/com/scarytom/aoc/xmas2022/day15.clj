(ns com.scarytom.aoc.xmas2022.day15
  (:require
    [com.scarytom.aoc.utils :as utils]))

(defn manhattan-distance [[x1 y1] [x2 y2]]
  (+ (abs (- x1 x2)) (abs (- y1 y2))))

(defn within-sensor-range? [centre-coord range candidate-coord]
  (<= (manhattan-distance centre-coord candidate-coord) range))

(defn within-range? [sensor->range candidate-coord]
  (some (fn [[sensor-coord range]] (within-sensor-range? sensor-coord range candidate-coord)) sensor->range))

(defn read-sensor-and-closest-beacon [line]
  (let [[_ sx sy bx by] (re-matches #"Sensor at x=(-?\d+), y=(-?\d+): closest beacon is at x=(-?\d+), y=(-?\d+)" line)]
    [[(utils/to-int sx) (utils/to-int sy)] [(utils/to-int bx) (utils/to-int by)]]))

(defn read-sensor-data [lines]
  (reduce (fn [acc [sensor beacon]] (-> acc
                                        (assoc-in [:sensor->range sensor] (manhattan-distance sensor beacon))
                                        (update :beacons (fnil conj #{}) beacon)))
          {} (map read-sensor-and-closest-beacon lines)))

(defn x-intersections [y-coord [[x y] range]]
  (let [y-dist (manhattan-distance [x y] [x y-coord])
        x-dist (- range y-dist)]
    (cond
      (zero? x-dist) [x x]
      (pos? x-dist) [(- x x-dist) (+ x x-dist)])))

(defn count-known-empty-coords [y lines]
  (let [{:keys [sensor->range beacons]} (read-sensor-data lines)
        intersections (remove nil? (map (partial x-intersections y) sensor->range))
        beacon-count (count (filter #(= y (second %)) beacons))]
    (->> intersections
         (map (fn [[a b]] [a (inc b)]))
         (utils/simplify-ranges)
         (reduce (fn [acc [a b]] (+ acc (- b a))) 0)
         (+ (* -1 beacon-count)))))

(defn find-missing-beacon-tuning-freq [limit lines]
  (let [{:keys [sensor->range]} (read-sensor-data lines)]
    (->> (range (inc limit))
         (mapcat (fn [y]
                   (let [intersections (remove nil? (map (partial x-intersections y) sensor->range))]
                     (->> intersections
                          (map (fn [[a b]] [a (inc b)]))
                          (utils/simplify-ranges)
                          (mapcat (fn [[a b]] [[(dec a) y] [b y]]))
                          (filter #(<= 0 (first %) limit))))))
         (remove #(within-range? sensor->range %))
         (first)
         ((fn [[x y]] (+ y (* 4000000 x)))))))

(defn part-1 []
  (utils/read-input-file 2022 15 (partial count-known-empty-coords 2000000)))

(defn part-2 []
  (utils/read-input-file 2022 15 (partial find-missing-beacon-tuning-freq 4000000)))