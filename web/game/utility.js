/**
 * @author manuel
 * Genera una matrice 9x9 di bottoni, matrice di classe grid-container.
 * Funzione invocata al caricamento del body. 
 */
function addmatrice(){
    for( i=0; i<9; i++){
        for( j=0; j<9; j++){
            document.getElementById("matrice").appendChild(addButton(i, j));
        }
    }
}

/**
 * Crea un bottone per la generazione della matrice di gioco
 * al bottone viene assegnato un eventhandler, al click del bottone
 * viene invocata la funzione requestCella( i, j).
 * @param {int} i indica la riga della matrice
 * @param {int} j indica la colonna della matrice
 * @returns {Button} restituisce il bottone creato.
 */
function addButton(i, j){
    var element = document.createElement("button");
    element.setAttribute("class", "grid-item");
    var id = ""+i+","+j;
    element.setAttribute("id", id);
                
    element.onclick = function(){
        requestCella( i, j);
    };
    return element;
}
            
            
/*  Funzione invocata alla pressione del tasto Test
 *  Ottiene tramite id gli elementi 'riga' e 'colonna', da essi
 *  poi ottiene i value selezionati dall'utente nei selects.
 * 
 *  Poi passa tali indici ad un'altra funzione requestCella(indici)
 *  che si occuperà della gestione dell'invio della richiesta.
 */
function getValueForm(){
    var elriga = document.getElementById("riga");
    var elcol = document.getElementById("colonna");
    var i = elriga.options[elriga.selectedIndex].value;
    var j = elcol.options[elcol.selectedIndex].value;
    var id = ""+i+","+j;
                
    var idBottSelez = document.getElementById(id).getAttribute("id");
    requestCella( i, j);
}
            
/**
 * Gestisce la richiesta alla servlet GestioneGioco, richiede il 
 * valore della cella in posizione i,j. La risposta da parte di tale
 * servlet è ti tipo testo, un semplice numero, dove -1 indica una
 * bomba altrimenti indica il numero di bombe nelle prossimità
 * di tale cella.
 * @param {int} i riga della cella selezionata
 * @param {int} j colonna della cella selezionata
 */
function requestCella( i, j){
    var xhttp = new XMLHttpRequest();
    xhttp.responseType = "text";
    var id = ""+i+","+j;
    var url = "/MineSweeper/GestioneGioco?riga="+i+"&colonna="+j;
                
    xhttp.onreadystatechange = function(){
	if( this.readyState == 4 && this.status == 200){
                        
	    var valoreCella = xhttp.responseText;
            //Se la cella selezionata è una bomba
            if( valoreCella == -1){
		document.getElementById(id).setAttribute("class", "grid-item bomba disabled");
                document.getElementById(id).innerText = "B";
                //Setto visibile il modal box
                document.getElementById("myModal").style.display = "block";
            }else{  //se non è una bomba invece
		document.getElementById(id).setAttribute("class", "grid-item nonBomba disabled");
                document.getElementById(id).innerText = valoreCella;
            }
	    document.getElementById("selezionata").innerText = valoreCella;
        }
    };
    xhttp.open("GET", url, true);
    xhttp.send();
}