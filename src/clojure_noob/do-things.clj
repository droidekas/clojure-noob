(ns clojure-noob.core
  (:gen-class))

  (def asym-hobbit-body-parts [{:name "head" :size 3}
                               {:name "left-eye" :size 1}
                               {:name "left-ear" :size 1}
                               {:name "mouth" :size 1}
                               {:name "nose" :size 1}
                               {:name "neck" :size 2}
                               {:name "left-shoulder" :size 3}
                               {:name "left-upper-arm" :size 3}
                               {:name "chest" :size 10}
                               {:name "back" :size 10}
                               {:name "left-forearm" :size 3}
                               {:name "abdomen" :size 6}
                               {:name "left-kidney" :size 1}
                               {:name "left-hand" :size 2}
                               {:name "left-knee" :size 2}
                               {:name "left-thigh" :size 4}
                               {:name "left-lower-leg" :size 3}
                               {:name "left-achilles" :size 1}
                               {:name "left-foot" :size 2}])

(defn matching-part [part stringToAdd]
  {:name (clojure.string/replace (:name part) #"^left-" (str stringToAdd "-"))
  :size (:size part)})

(defn matching-part-list [part prefixes]
(for [prefix prefixes] (matching-part part prefix)))

(defn symmetrizer
  [asym-body-parts]
  (loop [remaining-asym-parts asym-body-parts final-body-parts []]
    (if (& (empty? remaining-asym-parts))
    final-body-parts
    (let [[part & remaining-parts] remaining-asym-parts]
      (recur remaining-parts
        (into final-body-parts
          (set [part (matching-part part)])))))))

(defn reduce-sym [asym-body-parts]
  (reduce (fn [final-body-parts part]
                (into final-body-parts
                (set [part (matching-part part)])))
                 [] asym-body-parts))


(defn reduce-sym-list [asym-body-parts prefixes]
        (reduce (fn [final-body-parts part]
                (into final-body-parts
                (set (conj (matching-part-list part prefixes) part))))
                [] asym-body-parts))


(defn mapset [f array]
  (set (map f array )))


(def prefixes ["left","right","bottom","top","center"])
