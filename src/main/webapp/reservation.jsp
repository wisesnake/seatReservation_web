<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>예약 확인</title>
    <link rel="stylesheet" href="./CSS/reservation.css" />
    <style>
        body {
            text-align: center;
        }

        #container {
            display: flex;
            flex-direction: column;
            align-items: center;
            margin-top: 100px;
        }

        #msg {
            color: red;
        }
    </style>
</head>
<body>
    <div id="container">
        <form name="reservation" method="get" action="reservation">
            예약희망 좌석번호: <input type="text" name="seatNo"><br>
            전화번호 뒷자리: <input type="text" name="reservationNum" id="phoneNum" onChange="fnValidate()"> <div id="msg">
            </div><br>
            <input type="submit" value="확인"> <input type="reset" value="다시입력">
        </form>
        <a href="menu.html">홈으로</a> <!-- "홈으로"를 실제 홈페이지 URL로 바꿔주세요 -->
    </div>

    <script type="text/javascript">
        function fnValidate() {
        	let checkMsg = "";
            let phoneNum = document.getElementById("phoneNum");
            let msg = document.getElementById("msg");

            if (phoneNum.value.length != 4) {
                msg.style.color = "red";
                checkMsg = "전화번호 뒷자리는 4자리 미만으로 작성하십시오.";
            }  else {
                msg.style.color = "green";
                checkMsg = "유효한 입력입니다.";
            }
            msg.innerText = checkMsg;
        }
    </script>
</body>
</html>
