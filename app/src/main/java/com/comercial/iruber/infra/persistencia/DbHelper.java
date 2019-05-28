package com.comercial.iruber.infra.persistencia;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
public class DbHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 10;
    private static final String DATABASE_NAME = "appiruber";




    public static final String TABELA_USUARIO = "usuario";
    public static final String USUARIO_ID = "usuarioId";
    public static final String USUARIO_EMAIL = "email";
    public static final String USUARIO_SENHA = "senha";
    public static final String USUARIO_TIPO = "tipo";


    public static final String TABELA_CLIENTE = "cliente";
    public static final String CLIENTE_ID="idCliente";
    public static final String PESSOA_NOME = "nome";
    public static final String PESSOA_NASCIMENTO = "nascimento";
    public static final String PESSOA_CPF = "cpf";
    public static final String PESSOA_ENDERECO_ID = "idEndereco";
    public static final String PESSOA_USER_ID="idUser";


    public static final String TABELA_RESTAURANTE = "restaurante";
    public static final String RESTAURANTE_ID = "idRestaurante";
    public static final String RESTAURANTE_NOME = "nome";
    public static final String RESTAURANTE_USER_ID = "idPessoa";
    public static final String RESTAURANTE_CNPJ = "cnpj";
    public static final String RESTAURANTE_ID_ENDERECO= "idEndereco";

    public static final String TABELA_INGREDIENTE = "ingrediente";
    public static final String INGREDIENTE_ID_PRATO="idPrato";
    public static final String INGREDIENTE_ID = "idIngrediente";
    public static final String INGREDIENTE_NOME = "nome";
    public static final String INGREDIENTE_DISPONIVEL="disponivel";

    public static final String TABELA_PRATO = "prato";
    public static final String PRATO_ID ="idPrato";
    public static final String PRATO_RESTAURANTE_ID="idRestaurante";
    public static final String PRATO_NOME="nome";
    public static final String PRATO_DESCRICAO="pratoDescricao";
    public static final String PRATO_DISPONIVEL="pratoDisponivel";
    public static final String PRATO_VALOR="valor";
    public static final String PRATO_ID_INGREDIENTE="idIngrediente";




    public static final String TABELA_ENDERECO = "endereco";
    public static final String ID_ENDERECO = "idEndereco";
    public static final String ENDERECO_NUMERO= "numero";
    public static final String ENDERECO_CEP = "cep";
    public static final String ENDERECO_ESTADO="estado";
    public static final String ENDERECO_BAIRRO="bairro";
    public static final String ENDERECO_CIDADE="cidade";
    public static final String ENDERECO_RUA="rua";


    public static final String TABELA_PRATO_RESTAURANTE="prato_restaurante";
    public static final String PRATO_RESTAURANTE_ID_PRATO_RESTAURANTE="id_prato_restaurante";
    public static final String PRATO_RESTAURANTE_ID_RESTAURANTE="idRestaurante";
    public static final String PRATO_RESTAURANTE_ID_PRATO="idPrato";


    public static  final String TABELA_PRATO_INGREDIENTE="prato_ingrediente";
    public static final String PRATO_INGREDIENTE_ID="prato_ingrediente_id";
    public static final String PRATO_INGREDIENTE_ID_PRATO="idPrato";
    public static final String PRATO_INGREDIENTE_ID_INGREDIENTE="idIngrediente";

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+TABELA_USUARIO+"(" +
                USUARIO_ID+ " INTEGER PRIMARY KEY AUTOINCREMENT," +
                USUARIO_SENHA+" TEXT NOT NULL, " +
                USUARIO_EMAIL+" TEXT NOT NULL," +
                USUARIO_TIPO+" TEXT ); ");

        db.execSQL("CREATE TABLE "+TABELA_CLIENTE+" (" +
                CLIENTE_ID+" INTEGER PRIMARY KEY AUTOINCREMENT," +
                PESSOA_NOME+" TEXT NOT NULL," +
                PESSOA_NASCIMENTO +" TEXT NOT NULL," +
                PESSOA_CPF+" TEXT NOT NULL,"+
                PESSOA_USER_ID+" TEXT NOT NULL,"+
                PESSOA_ENDERECO_ID+" INTEGER NOT NULL);");

        db.execSQL("CREATE TABLE "+TABELA_RESTAURANTE+" (" +
                RESTAURANTE_ID+" INTEGER PRIMARY KEY AUTOINCREMENT," +
                RESTAURANTE_USER_ID +" INTEGER NOT NULL," +
                RESTAURANTE_NOME +" TEXT," +
                RESTAURANTE_CNPJ+" TEXT NOT NULL," +
                RESTAURANTE_ID_ENDERECO+" INTEGER NOT NULL);");

        db.execSQL("CREATE TABLE "+TABELA_ENDERECO+" (" +
                ID_ENDERECO+" INTEGER PRIMARY KEY AUTOINCREMENT," +
                ENDERECO_RUA+" TEXT NOT NULL, " +
                ENDERECO_NUMERO+" TEXT NOT NULL," +
                ENDERECO_CEP+" TEXT NOT NULL," +
                ENDERECO_BAIRRO+" TEXT NOT NULL," +
                ENDERECO_CIDADE+" TEXT NOT NULL," +
                ENDERECO_ESTADO+" TEXT NOT NULL); ");

        db.execSQL("CREATE TABLE "+TABELA_INGREDIENTE+"(" +
                INGREDIENTE_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+
                INGREDIENTE_NOME+ "TEXT NOT NULL," +
                INGREDIENTE_DISPONIVEL+"TEXT NOT NULL," +
                INGREDIENTE_ID_PRATO+"TEXT);");

        db.execSQL("CREATE TABLE "+TABELA_PRATO+"(" +
                PRATO_ID+" INTEGER PRIMARY KEY AUTOINCREMENT," +
                PRATO_NOME+" TEXT NOT NULL, " +
                PRATO_DESCRICAO+" TEXT NOT NULL," +
                PRATO_DISPONIVEL+" TEXT NOT NULL," +
                PRATO_VALOR+" TEXT NOT NULL," +
                PRATO_ID_INGREDIENTE+" TEXT NOT NULL," +
                PRATO_RESTAURANTE_ID+"TEXT NOT NULL); ");

        db.execSQL("CREATE TABLE "+TABELA_PRATO_INGREDIENTE+"(" +
                PRATO_INGREDIENTE_ID+" INTEGER PRIMARY KEY AUTOINCREMENT," +
                PRATO_INGREDIENTE_ID_INGREDIENTE+" INTEGER NOT NULL," +
                PRATO_INGREDIENTE_ID_PRATO+" INTEGER NOT NULL);");

        db.execSQL("CREATE TABLE "+TABELA_PRATO_RESTAURANTE+"(" +
                PRATO_RESTAURANTE_ID_PRATO_RESTAURANTE+" INTEGER PRIMARY KEY AUTOINCREMENT," +
                PRATO_RESTAURANTE_ID_PRATO+" INTEGER NOT NULL," +
                PRATO_RESTAURANTE_ID_RESTAURANTE+" INTEGER NOT NULL);");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE "+TABELA_USUARIO+" ");
        db.execSQL("DROP TABLE "+TABELA_CLIENTE+";");
        db.execSQL("DROP TABLE "+TABELA_RESTAURANTE+";");
        db.execSQL("DROP TABLE "+TABELA_ENDERECO+";");
        db.execSQL("DROP TABLE "+TABELA_INGREDIENTE+";");
        db.execSQL("DROP TABLE "+TABELA_PRATO+";");
        db.execSQL("DROP TABLE "+TABELA_PRATO_RESTAURANTE+";");
        db.execSQL("DROP TABLE "+TABELA_PRATO_INGREDIENTE+";");
        this.onCreate(db);
    }
}


