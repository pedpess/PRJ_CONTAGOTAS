package com.example;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Property;
import de.greenrobot.daogenerator.Schema;
import de.greenrobot.daogenerator.ToMany;

public class MyDaoGenerator {

    private static Schema schema = new Schema(3, "com.contagotas.data.DAO");

    private static Entity mediaGastos = schema.addEntity("MediaGastos");
    private static Entity detalheGastos = schema.addEntity("DetalheGastos");

    public static void main(String args[]) {

        createMediaGastos(schema);
        createDetalheGastos(schema);
        try {
            new DaoGenerator().generateAll(schema, args[0]);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static void createDetalheGastos(Schema schema) {

        detalheGastos.addIdProperty().autoincrement().primaryKey().index();
        Property detalhe_gastos = detalheGastos.addLongProperty("media_gastos_id").getProperty();
        detalheGastos.addIntProperty("maquina");
        detalheGastos.addIntProperty("tanque");
        detalheGastos.addIntProperty("privada");
        detalheGastos.addIntProperty("torneira");
        detalheGastos.addIntProperty("chuveiro");
        detalheGastos.addIntProperty("pia");
        detalheGastos.addIntProperty("lava_louca");
        detalheGastos.addStringProperty("deleted");

        //region Relations

        detalheGastos.addToOne(mediaGastos, detalhe_gastos);

        //endregion

    }

    private static void createMediaGastos(Schema schema) {

        mediaGastos.addIdProperty().autoincrement().primaryKey().index();
        mediaGastos.addIntProperty("total");
        mediaGastos.addStringProperty("data");
        mediaGastos.addStringProperty("deleted");
    }

}
