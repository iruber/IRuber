package com.comercial.iruber.usuario.gui;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.comercial.iruber.R;
import com.comercial.iruber.cliente.dominio.Cliente;
import com.comercial.iruber.infra.EnumTipo;
import com.comercial.iruber.infra.servicos.MaskEditUtil;
import com.comercial.iruber.infra.servicos.Validacao;
import com.comercial.iruber.restaurante.dominio.Restaurante;
import com.comercial.iruber.usuario.dominio.Cep;
import com.comercial.iruber.usuario.dominio.Endereco;
import com.comercial.iruber.usuario.dominio.Usuario;
import com.comercial.iruber.usuario.negocio.ServicoCadastrar;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class CadastroUsuarioActivity extends AppCompatActivity {
    protected static EnumTipo tipo;
    private Spinner campoCidade;
    private Spinner campoEstado;
    private EditText campoNome;
    private EditText campoEmail;
    private EditText campoCEP;
    private EditText campoDocumento;
    private EditText campoNumero;
    private EditText campoRua;
    private EditText campoNascimento;
    private EditText campoBairro;
    private EditText campoSenha;
    private EditText campoCelular;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_usuario);
        encontraView();
        if (Objects.equals(tipo.getDescricao(), EnumTipo.RESTAURANTE.getDescricao())) {
           campoDocumento.setHint("CNPJ");
           campoNascimento.setVisibility(View.INVISIBLE);
            campoDocumento.addTextChangedListener(MaskEditUtil.mask(campoDocumento, MaskEditUtil.FORMAT_CNPJ));
       } else {
            campoDocumento.addTextChangedListener(MaskEditUtil.mask(campoDocumento, MaskEditUtil.FORMAT_CPF));
        }
        campoNascimento.addTextChangedListener(MaskEditUtil.mask(campoNascimento, MaskEditUtil.FORMAT_NASCIMENTO));
        campoCEP.addTextChangedListener(MaskEditUtil.mask(campoCEP, MaskEditUtil.FORMAT_CEP));

        campoCelular.addTextChangedListener(MaskEditUtil.mask(campoCelular, MaskEditUtil.FORMAT_FONE));
    }

    private void encontraView() {
        this.campoCEP = findViewById(R.id.inputCEP);
        this.campoCelular = findViewById(R.id.inputCelular);
        this.campoNascimento = findViewById(R.id.inputNascimento);
        this.campoBairro = findViewById(R.id.inputBairro);
        this.campoSenha = findViewById(R.id.inputSenha);
        this.campoDocumento = findViewById(R.id.inputDocumento);
        this.campoCidade = findViewById(R.id.inputCidade);
        this.campoNumero = findViewById(R.id.inputNumero);
        this.campoEstado = findViewById(R.id.inputEstado);
        this.campoRua = findViewById(R.id.inputRua);
        this.campoEmail = findViewById(R.id.inputEmail);
        this.campoNome = findViewById(R.id.inputNome);
        Button botaoCadastrar = findViewById(R.id.btnCadastrarUsuario);
        botaoCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cadastrar();
            }
        });
    }

    private void cadastrar() {
        if (!verificarCampos()) {
            //valida campos
        } else {
            ServicoCadastrar servicoCadastrar = new ServicoCadastrar(getApplicationContext());
            String resultado = "Cadastrado com Sucesso";
            try {
                if (tipo.getDescricao().equals(EnumTipo.RESTAURANTE.getDescricao())) {
                    servicoCadastrar.cadastrarRestaurante(this.criarRestaurante());
                } else {
                    servicoCadastrar.cadastrarCliente(this.criarCliente());
                }
                Toast.makeText(getApplicationContext(), resultado, Toast.LENGTH_SHORT).show();
                Intent login = new Intent(this, MainLogin.class);
                startActivity(login);
                this.finish();
            } catch (Exception e) {
                resultado = e.getMessage();
                Toast.makeText(getApplicationContext(), resultado, Toast.LENGTH_LONG).show();
            }
        }
    }

    private Restaurante criarRestaurante() {
        Restaurante restaurante = new Restaurante();
        String nome = campoNome.getText().toString().trim();
        String cnpj = campoDocumento.getText().toString().trim();
        restaurante.setUsuario(criarUsuario());
        restaurante.setEndereco(criarEndereco());
        restaurante.setNome(nome);
        restaurante.setCnpj(cnpj);
        restaurante.setTelefone(campoCelular.getText().toString());
        return restaurante;

    }

    private Cliente criarCliente() throws ParseException {
        Cliente cliente = new Cliente();
        String nome = campoNome.getText().toString().trim();
        String nascimento = campoNascimento.getText().toString();
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        Date data =  format.parse(nascimento);
        String cpf = campoDocumento.getText().toString().trim();
        cliente.setUsuario(criarUsuario());
        cliente.setEndereco(criarEndereco());
        cliente.setNome(nome);
        cliente.setNascimento(data);
        cliente.setCpf(cpf);
        cliente.setTelefone(campoCelular.getText().toString());
        return cliente;

    }

    private Usuario criarUsuario() {
        String email = campoEmail.getText().toString().trim();
        String senha = campoSenha.getText().toString().trim();
        Usuario usuario = new Usuario();
        usuario.setEmail(email);
        usuario.setSenha(senha);
        usuario.setTipo(tipo);
        return usuario;
    }

    private Endereco criarEndereco() {
        Endereco endereco = new Endereco();
        String numero = campoNumero.getText().toString().trim();
        String cep = campoCEP.getText().toString().trim();
        String bairro = campoBairro.getText().toString().trim();
        String estado = campoEstado.getSelectedItem().toString().trim();
        String cidade = campoCidade.getSelectedItem().toString().trim();
        String rua = campoRua.getText().toString();
        endereco.setCidade(cidade);
        endereco.setNumero(numero);
        endereco.setBairro(bairro);
        endereco.setEstado(estado);
        endereco.setCep(cep);
        endereco.setRua(rua);
        return endereco;

    }

    private boolean verificarCampos() {
        encontraView();
        Validacao validar = new Validacao();
        if (validarCamposEndereco(validar)) return false;
        return !validarDadosPessoais(validar);
    }

    private boolean validarDadosPessoais(Validacao validar) {
        if (!validar.verificarCampoNome(this.campoNome.getText().toString())) {
            campoNome.setError("Campo Incorreto, verifique se o campo não está vazio " +
                    "e que não possui Caracteres especiais!");
            return true;
        }
        if (!validar.verificarCampoEmail(this.campoEmail.getText().toString())) {
            campoEmail.setError("Campo Incorreto, verifique se o campo não está vazio " +
                    "e que o e-mail digitado está correto!");
            return true;
        }
        if (!validar.verificarCampoSenha(this.campoSenha.getText().toString())) {
            campoSenha.setError("Senha deve conter no minimo 8 caracteres!");
            return true;
        }
        if (!validar.verificarCampoCelular(this.campoCelular.getText().toString())) {
            campoCelular.setError("Campo celular incorreto, deve possuir 11 digitos, " +
                    "possuindo ddd e numero com o 9");
            return true;
        }
        if (tipo == EnumTipo.RESTAURANTE) {
            if (!validar.validarCNPJ(MaskEditUtil.unmask(this.campoDocumento.getText().toString()))) {
                campoDocumento.setError("CNPJ incorreto!");
                return true;
            }
        } else {
            if (!validar.verificarCampoNascimento(this.campoNascimento.getText().toString())) {
                campoNascimento.setError("Campo nascimento deve conter dia, mês e ano de nascimento");
                return true;
            }
            if (!validar.validarCPF(MaskEditUtil.unmask(this.campoDocumento.getText().toString()))) {
                campoDocumento.setError("CPF incorreto!");
                return true;
            }
        }
        return false;
    }

    private boolean validarCamposEndereco(Validacao validar) {
        if (!validar.verificarCampoEstado(this.campoEstado.getSelectedItem().toString())) {
            setSpinnerError(campoEstado, "Estado!");
            return true;
        }
        if (!validar.verificarCampoCidade(this.campoCidade.getSelectedItem().toString())) {
            setSpinnerError(campoCidade, "Cidade!");
            return true;
        }
        if (!validar.verificarCampoCEP(this.campoCEP.getText().toString())) {
            campoCEP.setError("Campo CEP deve possuir 9 caracteres incluindo o '-'");
            return true;
        }
        if (!validar.verificarCampoBairro(this.campoBairro.getText().toString())) {
            campoBairro.setError("Campo bairro vazio!");
            return true;
        }
        if (!validar.verificarCampoNumero(this.campoNumero.getText().toString())) {
            campoNumero.setError("Campo numero vazio!");
            return true;
        }
        if (!validar.verificarCampoRua(this.campoRua.getText().toString())) {
            campoRua.setError("Campo vazio!");
            return true;
        }
        return false;
    }

    private void setSpinnerError(Spinner spinner, String error) {
        View selectedView = spinner.getSelectedView();
        if (selectedView instanceof TextView) {
            spinner.requestFocus();
            TextView selectedTextView = (TextView) selectedView;
            selectedTextView.setError("error"); // any name of the error will do
            selectedTextView.setTextColor(Color.RED); //text color in which you want your error message to be displayed
            selectedTextView.setText(error); // actual error message
            spinner.performClick(); // to open the spinner list if error is found.
        }
    }

    public void searchZipCode(View view) {
    }
    public String getUriRequest(){
        return "https://viacep.com.br/ws/"+getCEP()+"/json/";
    }

    private String getCEP() {
        return campoCEP.getText().toString();
    }
    public void lockFields( boolean isToLock ){
        //util.lockFields( isToLock );
    }


    public void setAddressFields(Cep address) {
    }
}
