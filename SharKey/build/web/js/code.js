$(document).ready(function() 
{
    // controlla la validità di un campo
    function controllaCampo($campo, min, max, idMsg, testoMsg) 
    {
        var $msg = $('#' + idMsg);

        if ($campo.val().length < min || $campo.val().length > max) 
        {
            $campo.addClass('error');
            $campo.removeClass('clear');

            if($msg.length === 0) 
            {
                var testoErrore = '<p id="' + idMsg + '" class="messaggioErrore">' + testoMsg + '</p>';
                $campo.next('.messaggioErrore').remove();
                $campo.after(testoErrore);
            }

            return false;
        } 

        else 
        {
            $campo.addClass('clear');
            $campo.removeClass('error');

            if($msg.length > 0) 
                $msg.remove();

            return true;
        }
    }

    // controlla la validità di tutti i campi
    function checkForm(formId, fields) 
    {
        var isValid = true;

        fields.forEach(function(field) 
        {
            isValid = controllaCampo($(field.selector), field.min, field.max, field.msgId, field.msgText) && isValid;
        });

        if(!isValid) 
        {
            alert("Errore nella compilazione dei campi");

            return false;
        }

        return true;
    }

    // aggiorna il conteggio dei caratteri di un campo
    function aggiornaConteggio($campo, $contatore, name_field, field) 
    {
        $campo.on('input', function() 
        {
            $contatore.text(name_field + " (" + $(this).val().length + "/" + field.max + ")");
            controllaCampo($campo, field.min, field.max, field.msgId, field.msgText);
        });
    }

    // --- DEFINIZIONE CAMPI ---

    // definizione dei campi di login
    var loginFields = 
    [
        { selector: '#username', min: 1, max: 50, msgId: 'msg1', msgText: 'Deve essere tra 1 e 50', name: "Username", $contatore: $('#caratteriUs') },
        { selector: '#password', min: 1, max: 50, msgId: 'msg2', msgText: 'Deve essere tra 1 e 50', name: "Password", $contatore: $('#caratteriPsw') }
    ];

    // definizione dei campi di registrazione
    var registrazioneFields = 
    [
        { selector: '#usernamer', min: 1, max: 50, msgId: 'msg3', msgText: 'Deve essere tra 1 e 50', name: "Username", $contatore: $('#caratteriUsRe') },
        { selector: '#email', min: 1, max: 50, msgId: 'msg4', msgText: 'Deve essere tra 1 e 50', name: "E-mail", $contatore: $('#caratteriEm') },
        { selector: '#passwordr', min: 1, max: 50, msgId: 'msg5', msgText: 'Deve essere tra 1 e 50', name: "Password", $contatore: $('#caratteriPswr') },
        { selector: '#nome', min: 1, max: 50, msgId: 'msg6', msgText: 'Deve essere tra 1 e 50', name: "Nome", $contatore: $('#caratteriNom') },
        { selector: '#cognome', min: 1, max: 50, msgId: 'msg7', msgText: 'Deve essere tra 1 e 50', name: "Cognome", $contatore: $('#caratteriCogn') }
    ];

    // definizione dei campi di inserimento del prodotto
    var productFields = 
    [
        { selector: '#key', min: 1, max: 30, msgId: 'msg8', msgText: 'Deve essere tra 1 e 30', name: "Product Key", $contatore: $('#caratteriKey') },
        { selector: '#nomep', min: 1, max: 50, msgId: 'msg9', msgText: 'Deve essere tra 1 e 50', name: "Titolo", $contatore: $('#caratteriNome') },
        { selector: '#descrizione', min: 1, max: 300, msgId: 'msg10', msgText: 'Deve essere tra 1 e 300', name: "Descrizione", $contatore: $('#caratteriDescrizione') },
        { selector: '#immagine', min: 1, max: 100, msgId: 'msg11', msgText: '', name: "Carica immagine", $contatore: $('#caratteriImmagine') }
    ];

    // definizione dei campi di inserimento della recensione
    var reviewFields = 
    [
        { selector: '#titolo', min: 1, max: 50, msgId: 'msg12', msgText: 'Deve essere tra 1 e 50', name: "Titolo", $contatore: $('#caratteriTitolo') },
        { selector: '#testo', min: 1, max: 300, msgId: 'msg13', msgText: 'Deve essere tra 1 e 300', name: "Recensione", $contatore: $('#caratteriRecensione') }
    ];

    // --- DEFINIZIONE EVENTI ---

    // aggiunta dell'evento di submit per il login
    $('#check_log').on('submit', function() 
    {
        return checkForm('#check_log', loginFields);
    });

    // aggiunta dell'evento di submit per la registrazione
    $('#check_re').on('submit', function() 
    {
        return checkForm('#check_re', registrazioneFields);
    });

    // aggiunta dell'evento di submit per l'inserimento del prodotto
    $('#check_pro').on('submit', function() 
    {
        return checkForm('#check_pro', productFields);
    });

    // aggiunta dell'evento di submit per l'inserimento della recensione
    $('#check_rev').on('submit', function() 
    {
        return checkForm('#check_rev', reviewFields);
    });

    // aggiorna il conteggio dei caratteri per ogni campo
    [loginFields, registrazioneFields, productFields, reviewFields].forEach(function(fields) 
    {
        fields.forEach(function(field) 
        {
            var $campo = $(field.selector);
            var $contatore = field.$contatore;

            aggiornaConteggio($campo, $contatore, field.name, field);
        });
    });
});
