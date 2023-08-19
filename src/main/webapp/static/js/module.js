// Get the modal
var modal = document.getElementById("myModal");
var modalCreate = document.getElementById("modalCreate");
var deleteModal = document.getElementById("modalDelete");

// Get the button that opens the modal
// var btn = document.getElementsByClassName("myBtn");
var btnCreate = document.getElementById("btnCreate");

// Get the <span> element that closes the modal
var span = document.getElementsByClassName("close")[0];
var span1 = document.getElementsByClassName("close")[1];
var span2 = document.getElementsByClassName("close")[2];

// When the user clicks on the button, open the modal

function GetData(id, sid, tid, suid, seid, room, start, end, score) {
    modal.style.display = "block";
    document.getElementById("id").value = id;
    document.getElementById("hocSinh").value = sid;
    document.getElementById("giaoVien").value = tid;
    document.getElementById("monHoc").value = suid;
    document.getElementById("hocKy").value = seid;
    document.getElementById("room").value = room;
    document.getElementById("start").value = start;
    document.getElementById("end").value = end;
    document.getElementById("diem").value = score;
}

function DeleteData(id) {
    deleteModal.style.display = "block";
    document.getElementById("module_id").value = id;
}

if (btnCreate != null) {
    btnCreate.onclick = function () {
        modalCreate.style.display = "block";
    }
}
// When the user clicks on <span> (x), close the modal
span.onclick = function () {
    modal.style.display = "none";
}
span1.onclick = function () {
    modalCreate.style.display = "none";
}
span2.onclick = function () {
    deleteModal.style.display = "none";
}
// When the user clicks anywhere outside of the modal, close it
window.onclick = function (event) {
    if (event.target == modal) {
        modal.style.display = "none";
    }
    if (event.target == modalCreate) {
        modalCreate.style.display = "none";
    }
    if (event.target == deleteModal) {
        deleteModal.style.display = "none";
    }
}

