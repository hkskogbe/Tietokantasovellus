<%
            if (session.getAttribute("lisattiinkayttaja") != null) {
                session.removeAttribute("lisattiinkayttaja");
        %>
        <div class="ilmoitus1">
            <fieldset>
                <p class="pienella">
                    K�ytt�j� lis�ttiin onnistuneesti tietokantaan.<br>
                </p>       
            </fieldset>
        </div>
        <%
            }
        %>


        <%
            if (session.getAttribute("virhelisattaessakayttajaa") != null) {
                session.removeAttribute("virhelisattaessakayttajaa");
        %>
        <div class="ilmoitus1">
            <fieldset>
                <p class="pienella">
                    Tapahtui virhe. Tarkista, ett� kaikki kent�t on t�ytetty.<br>
                </p>       
            </fieldset>
        </div>
        <%
            }
        %>
        
        <%
            if (session.getAttribute("kayttajanPoistoOnnistui") != null) {
                session.removeAttribute("kayttajanPoistoOnnistui");
        %>
        <div class="ilmoitus1">
            <fieldset>
                <p class="pienella">
                    K�ytt�j�n tiedot poistettiin onnistuneesti tietokannasta.<br>
                </p>       
            </fieldset>
        </div>
        <%
            }
        %>
        
        <%
            if (session.getAttribute("kayttajanPoistoEiOnnistunut") != null) {
                session.removeAttribute("kayttajanPoistoEiOnnistunut");
        %>
        <div class="ilmoitus1">
            <fieldset>
                <p class="pienella">
                    Tapahtui virhe poistettaessa k�ytt�j�� tietokannasta. Tarkista, ett� antamasi tunnus l�ytyy tietokannasta.<br>
                </p>       
            </fieldset>
        </div>
        <%
            }
        %>