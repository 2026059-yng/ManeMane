
// 固定費追加
document.getElementById("addFixedCost").addEventListener("click", () => {
    const area = document.getElementById("fixedCostArea");
    const div = document.createElement("div");
    div.classList.add("fixedCost");
    div.innerHTML = `
        <input type="text" class="fixedName" placeholder="固定費名">
        <input type="number" class="fixedAmount" placeholder="金額" min="0">
        <button type="button" class="deleteBtn">削除</button>`;
    div.querySelector(".fixedAmount").addEventListener("input", updateAvailableAmount);
    div.querySelector(".deleteBtn").addEventListener("click", () => {
            div.remove();
            updateAvailableAmount();
        });
    area.appendChild(div);
});

// 使用可能金額を計算して表示
function updateAvailableAmount() {

    const income =
        Number(document.getElementById("incomeAmount").value) || 0;

    let totalFixed = 0;

    document.querySelectorAll(".fixedAmount").forEach(fc => {

        const amount = Number(fc.value);

        if (!isNaN(amount) && amount >= 0) {
            totalFixed += amount;
        }
    });

    const available = income - totalFixed;

    document.getElementById("available_amount")
        .textContent = available.toLocaleString();
}

// 収入入力時に再計算
document.getElementById("incomeAmount")
    .addEventListener("input", updateAvailableAmount);

// 固定費追加
document.getElementById("addFixedCost").addEventListener("click", () => {

    const area = document.getElementById("fixedCostArea");

    const div = document.createElement("div");

    div.classList.add("fixedCost");

    div.innerHTML = `
        <input type="text" class="fixedName" placeholder="固定費名">
        <input type="number" class="fixedAmount" placeholder="金額" min="0">
        <button type="button" class="deleteBtn">削除</button>
    `;

    // 固定費入力時に再計算
    div.querySelector(".fixedAmount")
        .addEventListener("input", updateAvailableAmount);

    // 削除時に再計算
    div.querySelector(".deleteBtn")
        .addEventListener("click", () => {

            div.remove();
            updateAvailableAmount();
        });

    area.appendChild(div);
});


// 送信処理
document.getElementById("userForm").addEventListener("submit", function (event) {

    // 収入
    const incomeName = document.getElementById("incomeName").value.trim();
    const incomeAmount = Number(document.getElementById("incomeAmount").value);
    if (!incomeName || incomeAmount < 0) {
        alert("収入の入力（0以上）は必須です。");
        event.preventDefault();
        return;
    }

    // 固定費
    const fixedNames = [];
    const fixedAmounts = [];

    document.querySelectorAll(".fixedCost").forEach(fc => {
        const name = fc.querySelector(".fixedName").value.trim();
        const amount = Number(fc.querySelector(".fixedAmount").value);
        if (name && amount >= 0) {
            fixedNames.push(name);
            fixedAmounts.push(amount);
        }
    });

    // 収入と固定費を格納
    const allData = {
        incomeName: incomeName,
        incomeAmount: incomeAmount,
        categories: categories,
        fixedNames: fixedNames,
        fixedAmounts: fixedAmounts
    };

    document.getElementById("hiddenAllData").value = JSON.stringify(allData);

});
