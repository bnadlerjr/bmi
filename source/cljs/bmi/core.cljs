(ns bmi.core
  (:require [reagent.core :as reagent]))

(defn calculate-bmi
  [height-cm weight-kg]
  (let [height-m (/ height-cm 100)]
    (/ weight-kg (* height-m height-m))))

(defn update-state
  [state param event]
  (swap! state assoc param (.-target.value event)))

(defn bmi-component
  []
  (let [state (reagent/atom {:height 180 :weight 80})]
    (fn []
      (let [{:keys [height weight]} @state]
      [:div
       [:h1 "BMI Calculator"]
       [:div "Height: " height "cm"
        [:input {:type "range"
                 :value height
                 :min 100
                 :max 220
                 :on-change (partial update-state state :height)}]]
       [:div "Weight: " weight "kg"
        [:input {:type "range"
                 :value weight
                 :min 30
                 :max 150
                 :on-change (partial update-state state :weight)}]]
       [:div "BMI: " (int (calculate-bmi height weight))]]))))

(defn start
  []
  (reagent/render [bmi-component]
                  (.getElementById js/document "bmi-component")))

(defn stop
  [])

(defn ^:export init
  []
  (start))
