<%@taglib prefix="json" uri="http://www.atg.com/taglibs/json"%>
<%@page contentType="application/json" pageEncoding="UTF-8"%>

<json:object>
    <json:property name="immagine" value="${prodotto.getImmagine()}"/>
    <json:property name="titolo" value="${prodotto.getTitolo()}"/>
    <json:property name="prezzo" value="${prodotto.getPrezzo()}"/>
    <json:property name="descrizione" value="${prodotto.getDescrizione()}"/>
    <json:property name="dataUscita" value="${prodotto.getDataUscita()}"/>
    <json:property name="pegi" value="${prodotto.getPegi()}"/>
    <json:property name="venditore" value="${prodotto.getIdVenditore()}"/>
</json:object>