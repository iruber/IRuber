package com.comercial.iruber.usuario.gui;

import com.comercial.iruber.cliente.dominio.Cliente;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.comercial.iruber.R;
import com.comercial.iruber.cliente.dominio.Pessoa;
import com.comercial.iruber.usuario.dominio.Endereco;
import com.comercial.iruber.usuario.dominio.Usuario;
import com.comercial.iruber.usuario.negocio.ServicoCadastrar;
import com.comercial.iruber.usuario.persistencia.UsuarioDAO;


public class CadastroUsuarioActivity extends AppCompatActivity {


    private Button botaoCriar;
    private EditText campoNome, campoEmail,campoCEP,campoCpf,campoCidade,campoNumero,campoEstado,campoRua,campoidade,campoBairro,campoSenha;
    ServicoCadastrar servicoCadastrar;

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
        ServicoCadastrar servicoCadastrar = new ServicoCadastrar(getApplicationContext());



        try{
            servicoCadastrar.cadastrarCliente(this.criarCliente());
            Toast.makeText(getApplicationContext(),"cadastrou", Toast.LENGTH_SHORT);

        }
        catch (Exception e){
            e.printStackTrace();
            String resultado =e.getMessage();
            Toast.makeText(getApplicationContext(),resultado,Toast.LENGTH_LONG).show();
        }

    }

    private Pessoa criarPessoa(){

        String nome = campoNome.getText().toString();
        String idade=campoidade.getText().toString();
        String cpf=campoCpf.getText().toString();

        Pessoa pessoa = new Pessoa();

        pessoa.setNome(nome);
        pessoa.setIdade(idade);
        pessoa.setCpf(cpf);
        pessoa.setEndereco(this.criarEndereco());
        pessoa.setUsuario(this.criarUsuario());
        return pessoa;
    }

    private Usuario criarUsuario(){
        String email = campoEmail.getText().toString();
        String senha = campoSenha.getText().toString();
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
        String numero = campoNumero.getText().toString();
        String cep = campoCEP.getText().toString();
        String bairro= campoBairro.getText().toString();
        String estado= campoEstado.getText().toString();
        String cidade = campoCidade.getText().toString();
        endereco.setCidade(cidade);
        endereco.setNumero(numero);
        endereco.setBairro(bairro);
        endereco.setEstado(estado);
        endereco.setCep(cep);
        return endereco;

    }




}
