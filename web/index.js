/* index.js for MemoryGame*/


            /************************************************************
             *                                                          *
             *                                                          *
             *         Variables for Game Zone and Common View          *
             *                                                          *
             *                                                          *
             ************************************************************/
var gameZoneMasterResult = {};
var allPlayersIdArr = [];
var allPlayersPointsArr = [];
var allPlayersTrialArr = [];
var allPlayersNameArr = [];
var gameAnswer;
var gameAA = [];
var gameFlipped = [];
var gameFF;
var gameId;
var gameHScore;
var gameCurrentPlayerId;
var gameStatus;
var enterNameListArr = [];
var leaveNameListArr = [];
        


$(document).on("pagecreate","#homepage", function(){
    console.log("page create main");

    $("#common-view-games-list-view").on("click","a",function(){

    /******************************************************
     *                                                    *
     *                                                    *
     *      LOAD INFO FOR COMMON VIEW //0205_0346pm       *
     *                                                    *
     *                                                    *
     ******************************************************/
        
        //#1 -> get 1
        var $idsubstring = $(this).attr("href").substring(1);
        
        console.log(">>" + $idsubstring);
        
        /****************************************************************
         *                                                              *
         *                                                              *
         * Here get the Json Object from CommonViewResource.java:       *
         *                                                              *
         * JsonObject gameZoneAllInfo = Json.createObjectBuilder()      *
         *                 .add("game", gameJson)                       *
         *                 .add("players", playersTableJson)            *
         *                 .add("currentPlayerId", currentPlayerId)     *
         *                 .add("zeroPlayer", zeroPlayer)               *
         *                 .build();                                    *
         *                                                              *
         ****************************************************************/  
        
        $.getJSON("api/common-view/"+$idsubstring)
                .done(function(result){
                    
        initPlayers();            
                    
        gameZoneMasterResult = result;
            
        console.log(gameZoneMasterResult); //Object
            
        var thisGame = gameZoneMasterResult.game;
            gameAnswer = thisGame.answer;
            gameFlipped = thisGame.flipped;
            gameHScore = thisGame.hscore;
            gameId = thisGame.gameId;
            gameStatus = thisGame.status; //new added 0204-1043pm
            
        var thisPlayer = gameZoneMasterResult.currentPlayerId;    
            
        var hasNoPlayer = gameZoneMasterResult.zeroPlayer;
        
        if(hasNoPlayer==false){ //has player
            
            for(var i = 0 ; i < gameZoneMasterResult.players.length ; i++){
                var onePlayer = gameZoneMasterResult.players[i];
                allPlayersIdArr.push(onePlayer.playerId);
                allPlayersTrialArr.push(onePlayer.trial);
                allPlayersPointsArr.push(onePlayer.points);
                allPlayersNameArr.push(onePlayer.playerName);
            }

        }
        else{ //if hasNoPlayer is true
            allPlayersIdArr = [];
            allPlayersTrialArr=[];
            allPlayersPointsArr=[];
            allPlayersNameArr=[];
            
        }
        
        gameAA = parseAnswer();
        gameFF = parseFlipped();
 
            console.log("gameAnswer.toString() >> "+gameAnswer.toString());
            //[15, 05, 12, 04, 13, 02, 07, 15, 05, 14, 02, 06, 06, 04, 07, 01, 01, 13, 12, 14]
            console.log("gameFlipped.toString() >>"+gameFlipped.toString);
            //[0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]
            console.log("gameHScore >> "+gameHScore);
            //0
            console.log("gameId >> "+gameId);
            //7
            console.log("gameStatus >> "+ gameStatus); //new added 0204-1043pm
            //true
            console.log("allPlayersIdArr >> " + allPlayersIdArr.toString());
            //1,2,3
            console.log("allPlayersNameArr >> " + allPlayersNameArr);
            //Wendy, Gargi
            console.log("allPlayersTrialArr >> " + allPlayersTrialArr.toString());
            //8,7,4
            console.log("allPlayersPointsArr >>" + allPlayersPointsArr.toString());
            //2,4,2
            console.log("current player >> " + thisPlayer);
            //1
            console.log("gameAA >> " + gameAA);
            console.log("gameFF >> " + gameFF);
            
            $.mobile.navigate("#common-view-for-game", {transition:"flip"});
                
                });
    });
    


     /******************************************************
     *                                                    *
     *                                                    *
     *      LOAD INFO FOR GAME ZONE (PERSONAL VIEW)       *
     *                                                    *
     *                                                    *
     ******************************************************/

    $("#games").on("click","a",function(){

        //#1 -> get 1
        var $idsubstring = $(this).attr("href").substring(1);
        
        console.log(">>" + $idsubstring);
        
        
//        /****************************************************************
//         *                                                              *
//         *                                                              *
//         * Here get the Json Object from GameResource.java:             *
//         *                                                              *
//         * JsonObject gameZoneAllInfo = Json.createObjectBuilder()      *
//         *                 .add("game", gameJson)                       *
//         *                 .add("players", playersTableJson)            *
//         *                 .add("currentPlayerId", currentPlayerId)     *
//         *                 .add("zeroPlayer", zeroPlayer)               *
//         *                 .build();                                    *
//         *                                                              *
//         ****************************************************************/  

                $.getJSON("api/game/"+$idsubstring)
                        .done(function(result){
                            gameZoneMasterResult = result;

                console.log(gameZoneMasterResult); //Object

                var thisGame = gameZoneMasterResult.game;
                gameAnswer = thisGame.answer;
                gameFlipped = thisGame.flipped;
                gameHScore = thisGame.hscore;
                gameId = thisGame.gameId;
                gameStatus = thisGame.status; //new added 0204-1043pm

                var thisPlayer = gameZoneMasterResult.currentPlayerId;    

                var hasNoPlayer = gameZoneMasterResult.zeroPlayer;
                
                if(hasNoPlayer==false){ //has player
            
                    for(var i = 0 ; i < gameZoneMasterResult.players.length ; i++){
                        var onePlayer = gameZoneMasterResult.players[i];
                        allPlayersIdArr.push(onePlayer.playerId);
                        allPlayersTrialArr.push(onePlayer.trial);
                        allPlayersPointsArr.push(onePlayer.points);
                        allPlayersNameArr.push(onePlayer.playerName);
                    }

                }
                else{ //if hasNoPlayer is true
                    allPlayersIdArr = [];
                    allPlayersTrialArr=[];
                    allPlayersPointsArr=[];
                    allPlayersNameArr=[];

                }

                gameAA = parseAnswer();
                gameFF = parseFlipped();

                console.log("gameAnswer.toString() >> "+gameAnswer);
                //[15, 05, 12, 04, 13, 02, 07, 15, 05, 14, 02, 06, 06, 04, 07, 01, 01, 13, 12, 14]
                console.log("gameFlipped.toString() >>"+gameFlipped);
                //[0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]
                console.log("gameHScore >> "+gameHScore);
                //0
                console.log("gameId >> "+gameId);
                //7
                console.log("gameStatus >> "+ gameStatus); //new added 0204-1043pm
                //true
                console.log("allPlayersIdArr >> " + allPlayersIdArr);
                //1,2,3
                console.log("allPlayersNameArr >> " + allPlayersNameArr);
                //Wendy, Gargi
                console.log("allPlayersTrialArr >> " + allPlayersTrialArr);
                //8,7,4
                console.log("allPlayersPointsArr >>" + allPlayersPointsArr);
                //2,4,2
                console.log("current player >> " + thisPlayer);
                //1
                console.log("gameAA >> " + gameAA);
                console.log("gameFF >> " + gameFF);
                
                
//                $.get("enterGame", {
//                   gid : gameId
//                });
                
                
                
                
                $.mobile.navigate("#gameZone", {transition:"flip"});
                
                
                
                
                
                
                
                
        });

    });
 
});


//$(document).on("pagecreate","#common-view-for-game", function(){
//    
//});



$(document).on("pagebeforeshow", function(_,$ui){
    
    var source = null;
    
    //enter common view game page
    if(("common-view-for-game"==$ui.toPage.attr("id")) && ("common-view-all-games-page"==$ui.prevPage.attr("id"))){
        
        
        enterNameListArr = [];
        
        $("#common-view-output-2").text("");
        $("#common-view-output-3").text("");
 
        source = new EventSource("api/gameEvent");
           
        console.log("new event source");
        
        source.addEventListener(gameId, messageHandler);
    }
    
    
    //leave common view game page
    if(("common-view-all-games-page"==$ui.toPage.attr("id")) && ("common-view-for-game"==$ui.prevPage.attr("id"))){
        
        
//        $.get("enterGame", {
//               gid : gameId
//        });
        
        if(source!=null){
            source.close();
        }
        
        
//        $("#leaveGameZone").on("click", function(){
//            $.get("enterGame", {
//               gid : gameId
//            });
//        });
        
    }
    
    if(("gamelistpage"==$ui.toPage.attr("id")) && ("gameZone"==$ui.prevPage.attr("id"))){
        
        $.get("enterGame", {
               gid : gameId
        });
        
        
    
    }
    
    if(("gamelistpage"==$ui.prevPage.attr("id")) && ("gameZone"==$ui.toPage.attr("id"))){
        
        $.get("enterGame", {
               gid : gameId
        });
    }
    
    


    
});


$(document).on("pagecreate","#gameZone", function(){

        $(".custom-btn").css("background-image","url(images/purple.png)");


        var queue = [];
        var count = 0;

         $('.custom-btn').on("click", function() {
             
             $(".custom-btn").css("background-image","url(images/purple.png)");

             count++;
             
             if(count==1){
                 queue.push($(this).val());
             }
             else if(count==2 ){
                 if($(this).val() != queue[0]){
                     queue.push($(this).val());
                 }
                 else
                     count--;
             }
             else{
                 if(($(this).val() != queue[0]) && ($(this).val()!= queue[1])){
                     queue.push($(this).val());
                     queue.shift();
                 }
                 else
                     count--;
             }
            
            $("#first-input").val(queue[0]);
            console.log(">> queue[0] is "+ queue[0]);
            $("#second-input").val(queue[1]);
            console.log(">> queue[1] is "+ queue[1]);
            
            var btnId1 = "#opt"+queue[0];
            var btnId2 = "#opt"+queue[1];
            
            $(btnId1).css("background-image","url(images/dark-purple.png)");
            $(btnId2).css("background-image","url(images/dark-purple.png)");
            
            
            
            
        });
        
        
        /*
         * 
         * 
         * MOVED 0209 0504PM
         * 
         * 
         */
        
        //$("#leaveGameZone").on("click", function(){
//            $.get("enterGame", {
//               gid : gameId
//            });
        //});
        
 
           
        $("#submit-1").on("click", function(){

                //refresh ==> if (1)player have multiple trial (2) others are playing the same game 
                //the data must be up to date)
                refreshAllForGameZone(gameId);   

                var input1 = $("#first-input").val();
                var input2 = $("#second-input").val();
                $("#first-input").val("");
                $("#second-input").val("");
                var success;
                var isNew = false;

                var playersArray = parseAllPlayersIdArray();
                
                var thisPlayerIdx = playersArray.indexOf(gameCurrentPlayerId);
                
                var thisPlayerTrial = allPlayersTrialArr[thisPlayerIdx];
                console.log(">>thisPlayerTrial : "+ allPlayersTrialArr[thisPlayerIdx]);
                

                
                var flipArray = parseFlipped(); 
                
                if(input1=="" || input2==""){
                    alert("Please flip 2 cards.");
                }
                
                //1. no further action
                if(thisPlayerTrial < 1){
                    alert("You have no more trial.");
                }
                //2. no further action
                if((thisPlayerTrial >= 1) && ((flipArray[input1] == 1) ||(flipArray[input2]==1) && !(input1=="" || input2==""))){
                    alert("One of the card has been flipped. Please try again."); 
                } 

                if(!(thisPlayerTrial < 1)&&((flipArray[input1] != 1)&&(flipArray[input2]!=1)) && !(input1=="" || input2==""))
                {
                    //3. Is this guy a new player in this game?
                    if(checkNewPlayer()==true){
                        isNew = true;
                        console.log("isNewPlayer>> "+isNew);
                    }


                    //4. check answer right or wrong
                    if(validateAnswer(input1, input2)){
                        console.log(">>CORRRRRRRRRRRRRRRRRECT")
                        success = true;
                        alert("CORRRRRRRRRRRRRRRRRECT");
                    }
                    else{
                        console.log(">>WRRRRRRRRRRRRRRRRRRONG")
                        success = false;
                        alert("WRRRRRRRRRRRRRRRRRRONG");
                    }
                    
                    
                    $.get("validate", {
                        index1 : input1,
                        index2 : input2,
                        isSuccess : success,
                        isNewPlayer : isNew,
                        gid : gameId
                     });
                     
                     
//                     /* Flip card action here */
//
//                    var ansArray = parseAnswer();
//                    var toFlipImgId1 = ansArray[input1];
//                    var toFlipImgId2 = ansArray[input2];
//
//
//                    var btnId1 = "#opt"+input1;
//                    var btnId2 = "#opt"+input2;
//
//                    var url3 = "<img src='images/" + toFlipImgId1+ ".png' />'";
//                    var url4 = " <img src='images/" + toFlipImgId2+ ".png' />'";
//
//                    $(url3).appendTo(btnId1).attr("id", "card1");
//                    $(url4).appendTo(btnId2).attr("id", "card2");
//
//
//                    setTimeout(function() {  
//                        $("#card1").remove();
//                        $("#card2").remove();
//                        $(".custom-btn").css("background-image","url(images/purple.png)");
//
//                    }, 3000)
//
//                    queue = [];
//                    count = 0;  

                }

                /* Flip card action here */

                var ansArray = parseAnswer();
                var toFlipImgId1 = ansArray[input1];
                var toFlipImgId2 = ansArray[input2];


                var btnId1 = "#opt"+input1;
                var btnId2 = "#opt"+input2;

                var url3 = "<img src='images/resized/" + toFlipImgId1+ ".png' />'";
                var url4 = " <img src='images/resized/" + toFlipImgId2+ ".png' />'";

                $(url3).appendTo(btnId1).attr("id", "card1");
                $(url4).appendTo(btnId2).attr("id", "card2");


                setTimeout(function() {  
                    $("#card1").remove();
                    $("#card2").remove();
                    $(".custom-btn").css("background-image","url(images/purple.png)");

                }, 3000)

                queue = [];
                count = 0;  
                    
        });

});


$(document).on("pagecontainerbeforeshow", function(_,$ui){
    
    /*************************************
     *                                   *
     *                                   *
     * DISPLAY LIST VIEW OF ALL GAMES    *
     *                                   *
     *                                   *
     *************************************/
    if("gamelistpage"==$ui.toPage.attr("id")){
        
        $('#games').empty();

        $.getJSON("api/game")
        .done(function(result){
            for(var i in result){
                //$("#games").append(createLi(result[i.gameId].val());
                
                var obj = result[i];
                var stringInList = "Game ID: " + obj['gameId'] + ", No of players: " + obj['numOfPlayers'];
                var gameId = obj['gameId'];
                
                $("#games").append(createLi(stringInList, gameId));
                
                $("#games").listview("refresh");
            }
        });

    }
    
    
    /*****************************************************
     *                                                   *
     *                                                   *
     * DISPLAY LIST VIEW OF ALL GAMES for Common View    *
     *                                                   *
     *                                                   *
     *****************************************************/
    
    if("common-view-all-games-page"==$ui.toPage.attr("id")){
        
        $('#common-view-games-list-view').empty();

        $.getJSON("api/common-view")
        .done(function(result){
            for(var i in result){
                var obj = result[i];
                var stringInList = "Game ID: " + obj['gameId'] ;
                var gameId = obj['gameId'];
                
                $("#common-view-games-list-view").append(createLi(stringInList, gameId));
                
                $("#common-view-games-list-view").listview("refresh");
            }
        });
    }
    
    
    if("common-view-for-game"==$ui.toPage.attr("id")){
        
        console.log("gameFF>> " + gameFF);
        console.log("gameAA>> " + gameAA);
           
        var title = $("#common-view-title").text();
        $("#common-view-title").html(gameId);
           
        for(var i = 0 ; i < gameAA.length; i++){

            var sel = "#idx" + i;
            var source = "images/"+gameAA[i]+".png";

            if(gameFF[i]==1)
                $(sel).attr('src', source);
            else
                $(sel).attr('src', 'images/harold.png');

            console.log(">> gameFF = " + gameFF[i] + ">> gameAA = " + gameAA[i] );

        }
    }

    if("gameZone"==$ui.toPage.attr("id")){
        $("#input-show-answer").val(gameAnswer);
        $("#input-show-flipped").val(gameFlipped);

    }
    
    
    if("top-score-page"==$ui.toPage.attr("id")){
        
        $("#top-score-table tbody").empty();
        
        
        
        $.getJSON("api/rank")
                .done(function(result){
                    
                    var tbody = $('#top-score-table tbody'), props = ["playerId","score"];
                    
                    $.each(result, function(i,record){
                        var tr =$('<tr>');
                        //console.log("record >> " + record[i]);
                        
                        $.each(props, function(i,prop){
                            $('<td>').html(record[prop]).appendTo(tr);
                            console.log(">> record[prop] : " + record[prop]);
                        });
                        tbody.append(tr);
                    })
  
        });
    
    }


});


//////////////////////////////////////////

$(document).on("pagecontainershow", function(_,$ui){
  
})


/**************************************************
 *                                                *
 *                   Common View                  *
 *                                                *
 **************************************************/     


//$(window).load(function(){
//    $(window).on('pageshow', function() {
//       if($.mobile.activePage.attr("id") === "gameZone"){
//           
//        
//            
//        $.get("enterGame", {
//                   gid : gameId
//                });
//
//}})});

/**************** functions ****************/

function createLi(stringInList, gameId){
    
    var $a = $("<a>").attr("href","#"+gameId).text(stringInList);
    var $li = $("<li>").append($a);
    return ($li);
}

//0204-0742am
function messageHandler(message){
    console.log(">> handling message" + message.data);
    console.log("id: " + message.id);
    console.log("data: " + message.data);
    console.log("event: " + message.event);
    
    var str = message.data;
    var arr = str.split(" ");
    
    
    if(arr.length == 1){//enter event
        
        $("#common-view-output-2").text(message.data);
        var t = $("#common-view-output-2").text();
        
        console.log(">> t : " + t.toString());

        var isLeave=null;
        
        console.log(">> typeof enterNameListArr[0]" + typeof enterNameListArr[0]);

        if(enterNameListArr.length>0){
            
            //isLeave=false;
            
            for(var i = 0 ; i < enterNameListArr.length ; i++ ){
    
            console.log(">>enterNameListArr[i] " + enterNameListArr[i]);
                
                
                if(typeof enterNameListArr[i] != "undefined")
                {
                    var charArr1 = enterNameListArr[i].split("");
                    console.log(">> charArr1" + charArr1);
                    var charArr2 = t.split("");
                    console.log(">> charArr2" + charArr2);
                
                //if(enterNameListArr[i]== t){
                    if((charArr1[0]== charArr2[0]) && (charArr1[1]== charArr2[1]) && (charArr1[2]==charArr2[2])){
                        delete enterNameListArr[i];
                        isLeave = true;
                        break;
                
                
//                if(typeof enterNameListArr[0] === 'undefined'){
//                    isLeave = true;
//                }
                    
                }
                }else{isLeave = false;}
                    
                    
    
            }
            
        }else{
            isLeave=false;
        }
        
        
        if(isLeave==null)
            isLeave=false;
        

        //isLeave=false;

        console.log(">> isLeave "+ isLeave);

        if(isLeave == true){
            var cleanArr = cleanArray(enterNameListArr);
            console.log(">>enterNameListArr: " + cleanArr);        
            $("#common-view-output-3").text(cleanArr);
        }
        else if (isLeave==false){
            enterNameListArr.push(t);
            var cleanArr = cleanArray(enterNameListArr);
            console.log(">>enterNameListArr: " + cleanArr);
            $("#common-view-output-3").text(cleanArr);

        }
        
    }
    
    else{
        var idx1 = arr[0];
        var idx2 = arr[1];

        flipImage(idx1, idx2);

        var t = $("#common-view-output-1").text();
        $("#common-view-output-1").text(message.data + "\n" + t);
    }
    
    
    
}


function messageHandler2(message){
    console.log(">> handling message" + message);
    console.log("id: " + message.id);
    console.log("data: " + message.data);
    console.log("event: " + message.event);
    
    
    
    $("#common-view-output-2").text(message.data);
    var t = $("#common-view-output-2").text();
    
    
    var isLeave;
    

    for(var i = 0 ; i < enterNameListArr.length ; i++ ){
        
        if(t == enterNameListArr[i]){
            delete enterNameListArr[i];
            isLeave = true;
        }
    }
    
    isLeave=false;
    
    console.log(">> isLeave "+ isLeave);
    
    if(isLeave == true){
        var cleanArr = cleanArray(enterNameListArr);
        console.log(">>enterNameListArr: " + cleanArr);        
        $("#common-view-output-3").text(cleanArr);
    }
    else if (isLeave==false){
        enterNameListArr.push(t);
        var cleanArr = cleanArray(enterNameListArr);
        console.log(">>enterNameListArr: " + cleanArr);
        $("#common-view-output-3").text(cleanArr);
        
    }
  
//    var str = message.data;
//    var arr = str.split(" ");
//    var idx1 = arr[0];
//    var idx2 = arr[1];
//    
//    flipImage(idx1, idx2);

    //var t = $("#common-view-output-2").text();

    //$("#common-view-output-2").text(message.data + " " + t);
    
    
    
}



function checkNewPlayer(){
    
    console.log(">> checkNewPlayer()");
    
    var isNewPlayer = true;
    
    for(i=0 ; i < allPlayersIdArr.length ; i++ ){
        
        console.log(">> check new player");
        console.log(allPlayersIdArr[i]);
        console.log(gameZoneMasterResult.currentPlayerId);
        
        if(allPlayersIdArr[i]==gameZoneMasterResult.currentPlayerId){
            
            isNewPlayer=false;
            
        }
    }
    
    console.log(">> Is new player: " + isNewPlayer);
    console.log(Boolean(isNewPlayer));
    return Boolean(isNewPlayer);
}


function testSSE(){
    
    console.log(">> testSSE()");
    
    var input1 = $("#first-input").val();
    var input2 = $("#second-input").val();
    var inputArr = [];
    inputArr.push(input1);
    inputArr.push(input2);
    inputArr.push(gameId);
    var inputJsonString = JSON.stringify(inputArr);
    console.log(">> submit: "+ inputJsonString);

    $.get("validate",{message:inputJsonString});

    $("#first-input").val("");
    $("#second-input").val("");
}

function refreshAllForGameZone(gid){
    
    $.getJSON("api/game/"+gid)
                .done(function(result){
                    
        initPlayers();

        gameZoneMasterResult = result;

        var hasNoPlayer = gameZoneMasterResult.zeroPlayer;
        
        if(hasNoPlayer==false){//has player
            for(var i = 0 ; i < gameZoneMasterResult.players.length ; i++){
                var onePlayer = gameZoneMasterResult.players[i];
                allPlayersIdArr.push(onePlayer.playerId);
                allPlayersTrialArr.push(onePlayer.trial);
                allPlayersPointsArr.push(onePlayer.points);
                allPlayersNameArr.push(onePlayer.playerName);
            }
        }

        var thisGame = gameZoneMasterResult.game;
        gameAnswer = thisGame.answer;
        gameFlipped = thisGame.flipped;
        gameHScore = thisGame.hscore;
        gameId = thisGame.gameId;
        gameStatus = thisGame.status; //new added 0204-1043pm
        gameCurrentPlayerId = gameZoneMasterResult.currentPlayerId;
        
        console.log(">> current player id is " + gameCurrentPlayerId);
        console.log(">> allPlayersIdArr.toString() " + allPlayersIdArr.toString());
        console.log(">> allPlayersTrialArr.toString() " + allPlayersTrialArr.toString());
        console.log(">> allPlayersNameArr " + allPlayersNameArr);
        console.log(">> refreshed");

    });     
}


function validateAnswer(i1, i2){
    
    var isCorrect = false;
    
    var gameAA = parseAnswer();
    console.log(">>gameAA : " + gameAA);
    var a1 = gameAA[i1];
    console.log(">> "+a1);
    var a2 = gameAA[i2];
    console.log(">> "+a2);
    
    if(a1 == a2)
        isCorrect=true;
    
    return Boolean(isCorrect);

}

function initPlayers(){
    
    allPlayersIdArr = [];
    allPlayersPointsArr = [];
    allPlayersTrialArr = [];
    allPlayersNameArr = [];

}

function parseAnswer(){

    var str = gameAnswer.toString();
    str = str.substr(1,str.length-2);
    var res = str.split(", ");
 
    return res;

}

function parseFlipped(){
   
    var str = gameFlipped.toString();
    str = str.substr(1,str.length-2);
    var res = str.split(", ");
    
    return res;
}

function parseAllPlayersIdArray(){
    
    var str = allPlayersIdArr.toString();
    var res = str.split(",");
    return res;
}



function flipImage(idx1, idx2){
    
    var answerArray = parseAnswer();
    
    var sel1 = "#idx" + idx1;
    var sel2 = "#idx" + idx2;
    var source1 = "images/"+answerArray[idx1]+".png";
    var source2 = "images/"+answerArray[idx2]+".png";
    
    $(sel1).attr('src', source1);
    $(sel2).attr('src', source2);
    

}

function cleanArray(actual){
  var newArray = new Array();
  for(var i = 0; i<actual.length; i++){
      if (actual[i]){
        newArray.push(actual[i]);
    }
  }
  return newArray;
}
