package com.comercial.iruber.cliente.gui;

import com.comercial.iruber.cliente.dominio.Cliente;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.comercial.iruber.R;
import com.comercial.iruber.infra.servicos.Validacao;
import com.comercial.iruber.usuario.dominio.Endereco;
import com.comercial.iruber.usuario.dominio.Usuario;
import com.comercial.iruber.usuario.negocio.ServicoCadastrar;


public class CadastroClienteActivity extends AppCompatActivity {


    private Button botaoCriar;
    private EditText campoNome, campoEmail,campoCEP,campoCpf,campoCidade,campoNumero,campoEstado,campoRua,campoidade,campoBairro,campoSenha;
    Validacao validacao= new Validacao();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_cliente);
        encontraView();

    }

    private void encontraView() {
        this.campoCEP=findViewById(R.id.cepTextView);
        this.campoidade=findViewById(R.id.idadeTextView);
        this.campoBairro=findViewById(R.id.bairroTextView);
        this.campoSenha=findViewById(R.id.textViewSenha);
        this.campoCpf=findViewById(R.id.cpfTextView);
        this.campoCidade=findViewById(R.id.cidadeTextView);
        this.campoNumero=findViewById(R.id.numeroTextView);
        this.campoEstado=findViewById(R.id.estadoTextView);
        this.campoRua=findViewById(R.id.ruaTextView);
        this.campoEmail= findViewById(R.id.emailTextView);
        this.campoNome = findViewById(R.id.nomeTextView);
        this.botaoCriar=findViewById(R.id.cadastrarButton);
        this.botaoCriar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cadastrar();

            }
        });
    }


    private void cadastrar() {

            if(verificarCampos()!=true){
                return;
            }
        ServicoCadastrar servicoCadastrar = new ServicoCadastrar(getApplicationContext());


        String resultado="Cadastrado com Sucesso";
        try{
            servicoCadastrar.cadastrarCliente(this.criarCliente());
            Toast.makeText(getApplicationContext(),resultado, Toast.LENGTH_SHORT);

        }
        catch (Exception e){
            e.printStackTrace();
             resultado =e.getMessage();
            Toast.makeText(getApplicationContext(),resultado,Toast.LENGTH_LONG).show();
        }

    }

    private Pessoa criarPessoa(){

        String nome = campoNome.getText().toString().trim();
        String idade=campoidade.getText().toString().trim();
        String cpf=campoCpf.getText().toString().trim();

        Pessoa pessoa = new Pessoa();

        pessoa.setNome(nome);
        pessoa.setIdade(idade);
        pessoa.setCpf(cpf);
        pessoa.setEndereco(this.criarEndereco());
        pessoa.setUsuario(this.criarUsuario());
        return pessoa;
    }

    private Usuario criarUsuario(){
        String email = campoEmail.getText().toString().trim();
        String senha = campoSenha.getText().toString().trim();
        Usuario usuario = new Usuario();
        usuario.setEmail(email);
        usuario.setSenha(senha);
    return usuario;
    }

    private Cliente criarCliente(){
        Cliente cliente= new  Cliente();

        cliente.setPessoa(this.criarPessoa());

        return cliente;

    }

    private Endereco criarEndereco(){
        Endereco endereco = new Endereco();
        String numero = campoNumero.getText().toString().trim();
        String cep = campoCEP.getText().toString().trim();
        String bairro= campoBairro.getText().toString().trim();
        String estado= campoEstado.getText().toString().trim();
        String cidade = campoCidade.getText().toString().trim();
        endereco.setCidade(cidade);
        endereco.setNumero(numero);
        endereco.setBairro(bairro);
        endereco.setEstado(estado);
        endereco.setCep(cep);
        return endereco;

    }

    private boolean verificarCampos() {
        String nome = campoNome.getText().toString().trim();
        String email = campoEmail.getText().toString().trim();
        String senha = campoSenha.getText().toString().trim();
        String cep = campoCEP.getText().toString().trim();
        String cpf = campoCpf.getText().toString().trim();
        String idade = campoidade.getText().toString().trim();
        String cidade = campoCidade.getText().toString().trim();
        String estado = campoEstado.getText().toString().trim();
        String rua = campoRua.getText().toString().trim();
        String bairro = campoBairro.getText().toString().trim();
        String numero = campoNumero.getText().toString().trim();

        if (validacao.verificarCampoVazio(nome)) {
            this.campoNome.setError("Campo vazio");
            return false;
        } else if (validacao.verificarCampoEmail(email)) {
            this.campoEmail.setError("Formato de email inválido");
            return false;
        } else if (validacao.verificarCampoVazio(cidade)) {
            this.campoCidade.setError("Campo vazio");
            return false;
        } else if (validacao.verificarCampoVazio(idade)) {
            this.campoidade.setError("Campo vazio");
            return false;
        } else if (validacao.verificarCampoVazio(estado)) {
            this.campoEstado.setError("Campo vazio");
            return false;
        } else if (validacao.verificarCampoVazio(cep)) {
            this.campoCEP.setError("Campo vazio");
            return false;
        } else if (validacao.verificarCampoVazio(cpf)) {
            this.campoCpf.setError("Campo vazio");
            return false;
        } else if (validacao.verificarCampoVazio(rua)) {
            this.campoRua.setError("Campo vazio");
            return false;
        } else if (validacao.verificarCampoVazio(bairro)) {
            this.campoBairro.setError("Campo vazio");
            return false;
        } else if (validacao.verificarCampoVazio(numero)) {
            this.campoNumero.setError("Campo vazio");
            return false;
        } else if (validacao.verificarCampoVazio(senha)) {
            this.campoSenha.setError("Campo vazio");
            return false;



        } else {
            return true;
        }
    }



}
