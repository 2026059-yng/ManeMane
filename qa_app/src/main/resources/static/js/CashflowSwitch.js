// 今月ボタン
const buttonA = document.getElementById("thisMonth");

// 先月ボタン
const buttonB = document.getElementById("lastMonth");

// 今月の履歴画面
const screenA = document.querySelector(".cashflow-this");

// 先月の履歴画面
const screenB = document.querySelector(".cashflow-last");

// 画面の切り替え処理
buttonA.addEventListener('click', function(){
    buttonA.classList.remove('active');
    buttonB.classList.remove('active');
    screenA.classList.remove('active');
    screenB.classList.remove('active');
});

buttonB.addEventListener('click', function(){
    buttonA.classList.add('active');
    buttonB.classList.add('active');
    screenA.classList.add('active');
    screenB.classList.add('active');
});