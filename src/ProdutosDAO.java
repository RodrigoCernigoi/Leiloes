/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */
import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProdutosDAO {

    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();

    public void cadastrarProduto(ProdutosDTO produto) {

        conn = new conectaDAO().connectDB();
        try {
            prep = conn.prepareStatement("insert into produtos values(?,?,?,?)");
            prep.setInt(1, 0);
            prep.setString(2, produto.getNome());
            prep.setInt(3, produto.getValor());
            prep.setString(4, produto.getStatus());

            int i = prep.executeUpdate();
            System.out.println(i + " records inserted");

        } catch (SQLException ex) {
            Logger.getLogger(ProdutosDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public ArrayList<ProdutosDTO> listarProdutos() {
        conn = new conectaDAO().connectDB();
        try {
            prep = conn.prepareStatement("SELECT * from produtos");
            resultset = prep.executeQuery();

            while (resultset.next()) {
                int id = resultset.getInt("id");
                String nome = resultset.getString("nome");
                int valor = resultset.getInt("valor");
                String status = resultset.getString("status");

                ProdutosDTO produto = new ProdutosDTO();
                produto.setId(id);
                produto.setNome(nome);
                produto.setStatus(status);
                produto.setValor(valor);
                listagem.add(produto);

            }

        } catch (SQLException ex) {
            Logger.getLogger(ProdutosDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listagem;

    }
    
    public ArrayList<ProdutosDTO> listarProdutosVendidos() {
        conn = new conectaDAO().connectDB();
        try {
            prep = conn.prepareStatement("SELECT * from produtos WHERE status = 'Vendido'");
            resultset = prep.executeQuery();

            while (resultset.next()) {
                int id = resultset.getInt("id");
                String nome = resultset.getString("nome");
                int valor = resultset.getInt("valor");
                String status = resultset.getString("status");

                ProdutosDTO produto = new ProdutosDTO();
                produto.setId(id);
                produto.setNome(nome);
                produto.setStatus(status);
                produto.setValor(valor);
                listagem.add(produto);

            }

        } catch (SQLException ex) {
            Logger.getLogger(ProdutosDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listagem;

    }

    void venderProduto(int id) {
        conn = new conectaDAO().connectDB();
        try {
            prep = conn.prepareStatement("UPDATE produtos SET status = 'Vendido' WHERE id = ?");
            prep.setInt(1, id);
            prep.executeUpdate();


        } catch (SQLException ex) {
            Logger.getLogger(ProdutosDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
