<%
            if (session.getAttribute("lisattiinkayttaja") != null) {
                session.removeAttribute("lisattiinkayttaja");
        %>
        <div class="ilmoitus1">
            <fieldset>
                <p class="pienella">
                    Käyttäjä lisättiin onnistuneesti tietokantaan.<br>
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
                    Tapahtui virhe. Tarkista, että kaikki kentät on täytetty.<br>
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
                    Käyttäjän tiedot poistettiin onnistuneesti tietokannasta.<br>
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
                    Tapahtui virhe poistettaessa käyttäjää tietokannasta. Tarkista, että antamasi tunnus löytyy tietokannasta.<br>
                </p>       
            </fieldset>
        </div>
        <%
            }
        %>