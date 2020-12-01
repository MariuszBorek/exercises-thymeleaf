function showTheDivContent() {
    var table = document.getElementById('theDiv');
    if (table.style.display === 'none') {
        table.style.display = 'block';
    } else {
        table.style.display = 'none';
    }
}

function doSomething() {
    document.getElementById('theDiv').innerHTML = 'hoł hoł hoł';
}

function goToUserList() {
    window.location.href = "http://localhost:8080/api/users";
}

function goToMenu() {
    window.location.href = "http://localhost:8080/menu";
}

