package org.curso.vinos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class VinoDAO {

    public List<Vino> findAll() {
        List<Vino> list = new ArrayList<Vino>();
        Connection conn = null;
    	String sql = "SELECT * FROM vinos ORDER BY nombre";
        try {
            conn = ConnectionHelper.getConnection();
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                list.add(processRow(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
		} finally {
			ConnectionHelper.close(conn);
		}
        return list;
    }

    
    public List<Vino> findByName(String nombre) {
        List<Vino> lista = new ArrayList<Vino>();
        Connection conn = null;
    	String sql = "SELECT * FROM vinos " +
			"WHERE UPPER(nombre) LIKE ? " +	
			"ORDER BY nombre";
        try {
            conn = ConnectionHelper.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, "%" + nombre.toUpperCase() + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                lista.add(processRow(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
		} finally {
			ConnectionHelper.close(conn);
		}
        return lista;
    }
    
    public Vino findById(int id) {
    	String sql = "SELECT * FROM vinos WHERE id = ?";
        Vino vino = null;
        Connection conn = null;
        try {
            conn = ConnectionHelper.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                vino = processRow(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
		} finally {
			ConnectionHelper.close(conn);
		}
        return vino;
    }

    public Vino save(Vino vino) 	{
		return vino.getId() > 0 ? update(vino) : create(vino);
	}    
    
    public Vino create(Vino vino) {
        Connection conn = null;
        PreparedStatement ps = null;
        final String qry = "INSERT INTO vinos (nombre, uvas, pais, region, " 
        		+ "anyo, imagen, descripcion) " 
        		+ "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try {
            conn = ConnectionHelper.getConnection();            
			ps = conn.prepareStatement(qry , new String[] { "ID" });
            ps.setString(1, vino.getNombre());
            ps.setString(2, vino.getUvas());
            ps.setString(3, vino.getPais());
            ps.setString(4, vino.getRegion());
            ps.setString(5, vino.getAnyo());
            ps.setString(6, vino.getImagen());
            ps.setString(7, vino.getDescripcion());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            // Actualizar el id del objeto que se devuelve. Esto es importante 
            // ya que este valor debe ser devuelto al cliente.
            int id = rs.getInt(1);
            vino.setId(id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
		} finally {
			ConnectionHelper.close(conn);
		}
        return vino;
    }

    public Vino update(Vino vino) {
        Connection conn = null;
        try {
            conn = ConnectionHelper.getConnection();
            final String qry = "UPDATE vinos SET nombre=?, uvas=?, pais=?, region=?, " +
            			"anyo=?, imagen=?, descripcion=? WHERE id=?";
			PreparedStatement ps = conn.prepareStatement(qry );
            ps.setString(1, vino.getNombre());
            ps.setString(2, vino.getUvas());
            ps.setString(3, vino.getPais());
            ps.setString(4, vino.getRegion());
            ps.setString(5, vino.getAnyo());
            ps.setString(6, vino.getImagen());
            ps.setString(7, vino.getDescripcion());
            ps.setInt(8, vino.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
		} finally {
			ConnectionHelper.close(conn);
		}
        return vino;
    }

    public boolean remove(int id) {
        Connection conn = null;
        try {
            conn = ConnectionHelper.getConnection();
            PreparedStatement ps = conn.prepareStatement("DELETE FROM vinos WHERE id=?");
            ps.setInt(1, id);
            int count = ps.executeUpdate();
            return count == 1;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
		} finally {
			ConnectionHelper.close(conn);
		}
    }

    protected Vino processRow(ResultSet rs) throws SQLException {
        Vino vino = new Vino();
        vino.setId(rs.getInt("id"));
        vino.setNombre(rs.getString("nombre"));
        vino.setUvas(rs.getString("uvas"));
        vino.setPais(rs.getString("pais"));
        vino.setRegion(rs.getString("region"));
        vino.setAnyo(rs.getString("anyo"));
        vino.setImagen(rs.getString("imagen"));
        vino.setDescripcion(rs.getString("descripcion"));
        return vino;
    }
    
}
