const titleText = document.getElementById('title-text');
const descriptionTextarea = document.getElementById('description-textarea');
const releaseYearText = document.getElementById('release-year-text');
const languageSelect = document.getElementById('language-select');
const rentalDurationRange = document.getElementById('rental-duration-range');
const rentalDurationText = document.getElementById('rental-duration-text');
const rentalRateText = document.getElementById('rental-rate-text');
const lengthText = document.getElementById('length-text');
const replacementCostText = document.getElementById('replacement-cost-text');

const gRadio = document.getElementById('g-radio');
const pgRadio = document.getElementById('pg-radio');
const pg13Radio = document.getElementById('pg13-radio');
const rRadio = document.getElementById('r-radio');
const nc17Radio = document.getElementById('nc17-radio');

const submit = document.getElementById('submit');

// two-way value binding, field will reset to old value when input mismatches
rentalDurationText.value = rentalDurationRange.value;
rentalDurationRange.addEventListener('change', function () {
  rentalDurationText.value = rentalDurationRange.value;
  updateSubmit();
});
rentalDurationText.addEventListener('change', function () {
  let oldValue = rentalDurationRange.value;
  let newValue = parseInt(rentalDurationText.value) || undefined;
  if (newValue !== undefined && newValue > 0 && newValue < 10) {
    rentalDurationRange.value = newValue;
  } else {
    rentalDurationRange.value = oldValue;
    rentalDurationText.value = oldValue;
  }
  updateSubmit();
});

// bind the rest of controls using function definition
titleText.addEventListener('change', updateSubmit);
descriptionTextarea.addEventListener('change', updateSubmit);
languageSelect.addEventListener('change', updateSubmit);
rentalRateText.addEventListener('change', updateSubmit);
lengthText.addEventListener('change', updateSubmit);
replacementCostText.addEventListener('change', updateSubmit);
gRadio.addEventListener('change', updateSubmit);
pgRadio.addEventListener('change', updateSubmit);
pg13Radio.addEventListener('change', updateSubmit);
rRadio.addEventListener('change', updateSubmit);
nc17Radio.addEventListener('change', updateSubmit);

function updateSubmit() {
  submit.disabled =
      !titleText.value ||
      titleText.value.length > 128 ||
      descriptionTextarea.value.length > 255 ||
      !languageSelect.value ||
      !rentalRateText.value ||
      rentalRateText.value == 0 ||
      rentalRateText.value > 9999 ||
      !lengthText.value ||
      lengthText.value == 0 ||
      !replacementCostText.value ||
      replacementCostText.value == 0 ||
      replacementCostText.value > 99999 ||
      (!gRadio.checked && !pgRadio.checked && !pg13Radio.checked && !rRadio.checked && !nc17Radio.checked);
}
