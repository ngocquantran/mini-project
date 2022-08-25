$(function () {
    chooseSortBy();
    priceSlider();
})

function priceSlider() {
  const rangeInput = document.querySelectorAll(".range-input input");
  const progress = document.querySelector(".price-slider .progress");
  const minResult = document.querySelector(".min-price-tag");
  const maxResult = document.querySelector(".max-price-tag");
  let priceGap = 100;

  rangeInput.forEach(function (input) {
    input.addEventListener("input", (e) => {
      let minVal = parseInt(rangeInput[0].value);
      let maxVal = parseInt(rangeInput[1].value);

      if (maxVal - minVal < priceGap) {
        if (e.target.className === "range-min") {
          rangeInput[0].value = maxVal - priceGap;
        } else {
          rangeInput[1].value = minVal + priceGap;
        }
      } else {
        minResult.innerText = minVal;
        maxResult.innerText = maxVal;
        progress.style.left =
          ((minVal - $(".range-min").attr("min")) /
            ($(".range-min").attr("max") - $(".range-min").attr("min"))) *
            100 +
          "%";
        progress.style.right =
          (($(".range-max").attr("max") - maxVal) /
            ($(".range-max").attr("max") - $(".range-max").attr("min"))) *
            100 +
          "%";
      }
    });
  });
}

function chooseSortBy() {
    const $sortBox = $(".container__product-sort-box select");
    const $sortOptionNotice = $(".chosed-option.chosed-order");
    const $sortOptionName = $(".chosed-option.chosed-order span");
    $sortBox.change(function () {
        if ($sortBox.val() == "menu_order") {
            $sortOptionNotice.addClass("hidden");
        } else {
            $sortOptionNotice.removeClass("hidden");
            switch ($sortBox.val()) {
              case "popularity":
                $sortOptionName.text(" popularity");
                break;
              case "rating":
                $sortOptionName.text(" rating");
                break;
              case "date":
                $sortOptionName.text(" latest");
                break;
              case "price":
                $sortOptionName.text(" price low to high");
                break;
              case "price-desc":
                $sortOptionName.text(" price high to low");
                break;
            }
        }
    })
}