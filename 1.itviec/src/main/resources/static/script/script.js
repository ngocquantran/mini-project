$(function () {
    movingInputLabel();
    deleteEmployerAlert();
   handleEmployerAlert();
   handleJobAlert();
   deleteJobAlert();
})

function movingInputLabel() {
    const $input = $(".input-moving-lable input");
    const $label = $(".input-moving-lable input + label");
    $input.each(function (index, input) {
        if ($(input).val()) {
            $(input).addClass("focused");
        } else {
            $(input).focus(function () {
                $(input).addClass("focused");
            });
            $(input).focusout(function () {
                if (!$(input).val()) {
                    $(input).removeClass("focused");
                }
            });
        }
    });
}

//Employer JS------------------------------------------------------------------------------------------------

function deleteEmployerAlert() {
    const $deleteBtn = $(".employers .container .delete-btn");
    $deleteBtn.on("click", function (e) {
        if (confirm("Bạn có chắc chắn muốn xóa?") == false) {
            e.preventDefault();
        }
    })
}

function handleEmployerAlert(){
    const $btn=$(".edit-container .btn-submit button");
    $btn.on("click",function (e){
        if(isBoxChecked()==false){
            e.preventDefault();
            alert("Vui lòng chọn các tùy chọn  *");
        }
    })

}

function isBoxChecked(){
    const $typeBoxes=$(".edit-container .type input:checked");
    const $locationBoxes=$(".edit-container .location input:checked");
    if($typeBoxes.length>0 && $locationBoxes.length>0){
        return true;
    }else {
        return false;
    }
}

//Job JS------------------------------------------------------------------------------------------------

function deleteJobAlert() {
    const $deleteBtn = $(".jobs .container .delete-btn");
    $deleteBtn.on("click", function (e) {
        if (confirm("Bạn có chắc chắn muốn xóa?") == false) {
            e.preventDefault();
        }
    })
}

function handleJobAlert(){
    const $btn=$(".job-edit-container .btn-submit button");
    $btn.on("click",function (e){
        if(isJobBoxChecked()==false){
            e.preventDefault();
            alert("Vui lòng chọn các tùy chọn  *");
        }
    })

}

function isJobBoxChecked(){
    const $typeBoxes=$(".job-edit-container .skill input:checked");
    const $locationBoxes=$(".job-edit-container .location input:checked");
    if($typeBoxes.length>0 && $locationBoxes.length>0){
        return true;
    }else {
        return false;
    }
}