package util;

//import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
//import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//import entidad.Proveedor;


public class Excel {
	
    public static void main(String[] args) throws IOException, SQLException {
    	
     //  Reporte();

    }
	
	public static  void Reporte() throws IOException,SQLException {
		
		Workbook book = new XSSFWorkbook();
			Sheet sheet = (Sheet) book.createSheet("Proveedor");
			CellStyle tituloEstilo = book.createCellStyle();
			tituloEstilo.setAlignment(HorizontalAlignment.CENTER);
			tituloEstilo.setVerticalAlignment(VerticalAlignment.CENTER);
			
			Font tituloFuente=book.createFont();
			tituloFuente.setFontName("Arial");
			tituloFuente.setBold(true);
			tituloFuente.setFontHeightInPoints((short)14);
			tituloEstilo.setFont(tituloFuente);
			tituloFuente.setColor(IndexedColors.LIGHT_BLUE.getIndex());
			
			Row tituloFila = sheet.createRow(1);
			Cell celdaTitulo= tituloFila.createCell(1);
			celdaTitulo.setCellStyle(tituloEstilo);
			celdaTitulo.setCellValue("Reporte de Proveedor");
			
			//para indicar que se unan las celdas a lo cual pide 4 parametros
			sheet.addMergedRegion(new CellRangeAddress(1,2,1,3));
			
			String[] cabecera= new String[] {"IdProveedor","Nombres","apellidos","DNI","Dirección",
					"Teléfono","Correo","País","Fecha Registro"};
			
			CellStyle cabeceraEstilo=book.createCellStyle();
			cabeceraEstilo.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex());
			cabeceraEstilo.setFillPattern(FillPatternType.SOLID_FOREGROUND);
			cabeceraEstilo.setBorderBottom(BorderStyle.THIN);
			cabeceraEstilo.setBorderLeft(BorderStyle.THIN);
			cabeceraEstilo.setBorderTop(BorderStyle.THIN);
			cabeceraEstilo.setBorderRight(BorderStyle.THIN);
			
			Font cabeceraFuente=book.createFont();
			cabeceraFuente.setFontName("Arial");
			cabeceraFuente.setBold(true);
			cabeceraFuente.setColor(IndexedColors.WHITE.getIndex());
			cabeceraFuente.setFontHeightInPoints((short)12);
			cabeceraEstilo.setFont(cabeceraFuente);
			
			Row EncabezadoFila=sheet.createRow(8);
			for (int i = 0; i < cabecera.length; i++) {
				Cell EncabezadoCelda = EncabezadoFila.createCell(i);
				EncabezadoCelda.setCellStyle(cabeceraEstilo);
				EncabezadoCelda.setCellValue(cabecera[i]);
			}
			//ArrayList<Proveedor> data = new ArrayList<Proveedor>();
			Connection conn = null;
			PreparedStatement pstm = null;
			ResultSet rs = null;
			try {
				conn= MySqlDBConexion.getConexion();
				int numFilaDatos = 9;
				CellStyle datosEstilo = book.createCellStyle();
				datosEstilo.setBorderBottom(BorderStyle.THIN);
				datosEstilo.setBorderLeft(BorderStyle.THIN);
				datosEstilo.setBorderTop(BorderStyle.THIN);
				datosEstilo.setBorderRight(BorderStyle.THIN);
				String sql = "select*from Proveedor";
				pstm = conn.prepareStatement(sql);
				rs= pstm.executeQuery();
				int numCol= rs.getMetaData().getColumnCount();
				while(rs.next()) {
					Row filaDatos=sheet.createRow(numFilaDatos);
					for (int i = 0; i < numCol; i++) {
						Cell CeldaDatos=filaDatos.createCell(i);
						CeldaDatos.setCellStyle(datosEstilo);
						
						if(i==0)CeldaDatos.setCellValue(rs.getInt(i+1));
						else if(i>=1||i<8)CeldaDatos.setCellValue(rs.getString(i+1));
						else CeldaDatos.setCellValue(rs.getDate(i+1));
					}
					numFilaDatos++;
				}
				sheet.autoSizeColumn(0);
				sheet.autoSizeColumn(1);
				sheet.autoSizeColumn(2);
				sheet.autoSizeColumn(3);
				sheet.autoSizeColumn(4);
				sheet.autoSizeColumn(5);
				sheet.autoSizeColumn(6);
				sheet.autoSizeColumn(7);
				sheet.autoSizeColumn(8);
				sheet.setZoom(150);
				FileOutputStream ArchivoSalida = new FileOutputStream("C:\\ReporteProveedor.xlsx");
				book.write(ArchivoSalida);
				ArchivoSalida.close();
				
				
			} catch (FileNotFoundException ex) {Logger.getLogger(Excel.class.getName()).log(Level.SEVERE, null, ex);}
			catch (IOException ex) {	        Logger.getLogger(Excel.class.getName()).log(Level.SEVERE,null, rs);}
			finally {
				try {
					if(pstm!=null)pstm.close();
					if(conn!=null)conn.close();
					if(rs!=null)rs.close();
				}catch(Exception e2) {e2.printStackTrace();}
			}
		}
		
		
		
	}
	

