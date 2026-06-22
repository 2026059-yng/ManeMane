const buttonA = document.getElementById('home-out-button');
const buttonB = document.getElementById('home-in-button');
const buttonC = document.getElementById('home-submit-button');

const buttonD = document.getElementById('home-category1');
const buttonE = document.getElementById('home-category2');
const buttonF = document.getElementById('home-category3');

document.addEventListener("DOMContentLoaded", () => {
    const currentDate = new Date();
    const y = currentDate.getFullYear();
    const m = String("0" + (currentDate.getMonth() + 1)).slice(-2);
    const d = String("0" + currentDate.getDate()).slice(-2);
    document.getElementById("dateValue").value = y + '-' + m + '-' + d;

    document.getElementById("in_out").value = 'true';

    buttonA.classList.add('active');
})

function selectInOut(selectInout) {
    document.getElementById("in_out").value = selectInout;
}


function selectCategory(category_num) {
    if (category_num == "1") {
        document.getElementById("home-category_name").value = document.getElementById('home-category1').value;
    } else if (category_num == "2") {
        document.getElementById("home-category_name").value = document.getElementById('home-category2').value;
    } else {
        document.getElementById("home-category_name").value = document.getElementById('home-category3').value;
    }
}

// 1. 部品の取得
let button1 = document.getElementById('home-button1');
let button2 = document.getElementById('home-button2');
let button3 = document.getElementById('home-button3');
let button4 = document.getElementById('home-button4');
let button5 = document.getElementById('home-button5');
let text = document.getElementById('daily_amount');

// 2. データを記憶するポケットの作成
let count = 0;

function calcAmount(button) {
    if (button == "button1") {
        count += 100;
    } else if (button == "button2") {
        count += 500;
    } else if (button == "button3") {
        count += 1000;
    } else if (button == "button4") {
        count += 10000;
    } else {
        count = 0;
    }
    document.getElementById("daily_amount").value = count;
}

buttonA.addEventListener('click',function(){   // Aボタンをクリックした時
    buttonA.classList.add('active');
    buttonB.classList.remove('active');
    buttonC.classList.remove('in');
    buttonC.classList.add('out');
});

buttonB.addEventListener('click',function(){   // Bボタンをクリックした時
    buttonA.classList.remove('active');
    buttonB.classList.add('active');
    buttonC.classList.remove('out');
    buttonC.classList.add('in');
});

buttonD.addEventListener('click',function(){   // Dボタンをクリックした時
    buttonD.classList.add('active');
    buttonE.classList.remove('active');
    buttonF.classList.remove('active');
});

buttonE.addEventListener('click',function(){   // Eボタンをクリックした時
    buttonD.classList.remove('active');
    buttonE.classList.add('active');
    buttonF.classList.remove('active');
});
buttonF.addEventListener('click',function(){   // Fボタンをクリックした時
    buttonD.classList.remove('active');
    buttonE.classList.remove('active');
    buttonF.classList.add('active');
});
