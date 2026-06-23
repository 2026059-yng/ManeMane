
// 固定費計算（Qiita参考）
function updateAvailableAmount() {
    const income = Number(document.getElementById("incomeAmount").value) || 0;
    let total = 0;

    document.querySelectorAll(".fixedAmount").forEach(function (input) {
        total += Number(input.value) || 0;
    });

    const available = income - total;
    const disp = document.getElementById("available_amount");
    const err = document.getElementById("availableError");

    disp.textContent = available.toLocaleString();

    if (available < 0) {
        disp.style.color = "red";
        err.style.display = "block";
    } else {
        disp.style.color = "";
        err.style.display = "none";
    }
}

// 収入入力
document.getElementById("incomeAmount").addEventListener("input", updateAvailableAmount);

// 固定費追加（Qiita参考）
document.getElementById("addFixedCost").addEventListener("click", function () {
    const area = document.getElementById("fixedCostArea");
    const div = document.createElement("div");
    div.className = "fixedCost";
    div.innerHTML = `
        <input type="text" class="fixedName" name="fixedName" placeholder="固定費名">
        <input type="text" class="fixedAmount" name="fixedAmount" placeholder="金額">
        <button type="button" class="deleteBtn">削除</button>
    `;

    div.querySelector(".fixedAmount").addEventListener("input", function () {
        this.value = this.value.replace(/[^0-9]/g, "");// 半角に強制変換
        updateAvailableAmount();
    });

    div.querySelector(".deleteBtn").addEventListener("click", function () {
        div.remove();
        updateAvailableAmount();
    });

    area.appendChild(div);
});

// 数字のみ
document.querySelectorAll(".numberOnly").forEach(function (input) {
    input.addEventListener("input", function () {
        this.value = this.value.replace(/[^0-9]/g, "");
    });
});

// 送信処理
document.getElementById("userForm").addEventListener("submit", function (e) {
    const income = document.getElementById("incomeAmount").value.trim();
    if (!income) {
        alert("収入を入力してください。");
        e.preventDefault();
        return;
    }

    let totalFixed = 0;
    let error = false;
    const names = [];
    const amounts = [];

    document.querySelectorAll(".fixedCost").forEach(function (row) {
        const name = row.querySelector(".fixedName").value.trim();
        const amt = row.querySelector(".fixedAmount").value.trim();

        if (!name || !amt || Number(amt) <= 0) {
            error = true;
        } else {
            totalFixed += Number(amt);
            names.push(name);
            amounts.push(Number(amt));
        }
    });

    if (error) {
        alert("固定費の名前と金額を正しく入力してください。");
        e.preventDefault();
        return;
    }

    if (Number(income) - totalFixed < 0) {
        alert("固定費が収入を超えています。");
        e.preventDefault();
        return;
    }

    // カテゴリチェック
    if (!document.getElementById("cat1").value.trim() ||
        !document.getElementById("cat2").value.trim() ||
        !document.getElementById("cat3").value.trim()) {
        alert("カテゴリはすべて入力してください。");
        e.preventDefault();
        return;
    }

    const data = {
        incomeAmount: Number(income),
        categories: [
            document.getElementById("cat1").value.trim(),
            document.getElementById("cat2").value.trim(),
            document.getElementById("cat3").value.trim()
        ],
        fixedName: names,
        fixedAmount: amounts
    };

    document.getElementById("hiddenAllData").value = JSON.stringify(data);
});