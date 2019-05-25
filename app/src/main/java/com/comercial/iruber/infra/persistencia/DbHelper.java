package com.comercial.iruber.infra.persistencia;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
public class DbHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "appiruber";



    //USUÁRIO
    public static final String TABELA_USUARIO = "usuario";
    public static final String USUARIO_ID = "usuarioId";
    public static final String USUARIO_EMAIL = "email";
    public static final String USUARIO_SENHA = "senha";
    public static final String USUARIO_TIPO = "tipo";


    //CLIENTE
    public static final String TABELA_CLIENTE = "cliente";
    public static final String CLIENTE_ID="idCliente";
    public static final String PESSOA_NOME = "nome";
    public static final String PESSOA_IDADE = "idade";
    public static final String PESSOA_CPF = "cpf";
    public static final String PESSOA_ENDERECO_ID = "idEndereco";
    public static final String PESSOA_USER_ID="idUser";

    //RESTAURANTE

    public static final String TABELA_RESTAURANTE = "restaurante";
    public static final String RESTAURANTE_ID = "idRestaurante";
    public static final String RESTAURANTE_NOTA = "nota";
    public static final String RESTAURANTE_ID_PESSOA= "idPessoa";
    public static final String RESTAURANTE_ID_PRATO = "idDrato";
    public static final String RESTAUARNTE_ID_INGREDIENTE="idIngrediente";

    //ENDEREÇO


    public static final String TABELA_ENDERECO = "endereco";
    public static final String ID_ENDERECO = "idEndereco";
    public static final String ENDERECO_NUMERO= "numero";
    public static final String ENDERECO_CEP = "cep";
    public static final String ENDERECO_ESTADO="estado";
    public static final String ENDERECO_BAIRRO="bairro";
    public static final String ENDERECO_CIDADE="cidade";
    public static final String ENDERECO_RUA="rua";


    private String logradouro;
    private String numero;
    private String cidade;
    private String cep;
    private String bairro;
    private String estado;

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //Pessoa Física
        db.execSQL("CREATE TABLE "+TABELA_USUARIO+"(" +
                USUARIO_ID+ " INTEGER PRIMARY KEY AUTOINCREMENT," +
                USUARIO_SENHA+" TEXT NOT NULL, " +
                USUARIO_EMAIL+" TEXT NOT NULL," +
                USUARIO_TIPO+" TEXT ); ");



        db.execSQL("CREATE TABLE "+TABELA_CLIENTE+" (" +
                CLIENTE_ID+" INTEGER PRIMARY KEY AUTOINCREMENT," +
                PESSOA_NOME+" TEXT NOT NULL," +
                PESSOA_IDADE+" TEXT NOT NULL," +
                PESSOA_CPF+" TEXT NOT NULL,"+
                PESSOA_USER_ID+" TEXT NOT NULL,"+
                PESSOA_ENDERECO_ID+" INTEGER NOT NULL);");




        // Pessoa Jurídica



        db.execSQL("CREATE TABLE "+TABELA_RESTAURANTE+" (" +
                RESTAURANTE_ID+" INTEGER PRIMARY KEY AUTOINCREMENT," +
                RESTAURANTE_ID_PESSOA+" INTEGER NOT NULL," +
                RESTAURANTE_NOTA+" TEXT NOT NULL, " +
                RESTAUARNTE_ID_INGREDIENTE+" INTEGER," +
                RESTAURANTE_ID_PRATO+" INTEGER);");








        db.execSQL("CREATE TABLE endereco(" +
                ID_ENDERECO+" INTEGER PRIMARY KEY AUTOINCREMENT," +
                ENDERECO_RUA+"TEXT NOT NULL, " +
                ENDERECO_NUMERO+" TEXT NOT NULL," +
                ENDERECO_CEP+" TEXT NOT NULL," +
                ENDERECO_BAIRRO+" TEXT NOT NULL," +
                ENDERECO_CIDADE+" TEXT NOT NULL," +
                ENDERECO_ESTADO+" TEXT NOT NULL); ");

        db.execSQL("CREATE TABLE pedido(" +
                "idPedido INTEGER PRIMARY KEY AUTOINCREMENT," +
                "data TEXT NOT NULL, " +
                "valorTotal TEXT NOT NULL," +
                "statusPedido TEXT NOT NULL); ");


        db.execSQL("CREATE TABLE prato(" +
                "idPrato INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nome TEXT NOT NULL, " +
                "descricao TEXT NOT NULL," +
                "disponivel TEXT NOT NULL," +
                "valor TEXT NOT NULL," +
                "ingredientes TEXT NOT NULL); ");




        db.execSQL("CREATE TABLE entregador (" +
                "idEntregador INTEGER PRIMARY KEY AUTOINCREMENT," +
                "numeroEntregas TEXT NOT NULL); ");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE usuario;");
        db.execSQL("DROP TABLE cliente;");
        db.execSQL("DROP TABLE restaurante;");
        db.execSQL("DROP TABLE empresa;");
        this.onCreate(db);
    }
}


