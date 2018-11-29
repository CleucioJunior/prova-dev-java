package br.com.casadocodigo.loja.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.casadocodigo.loja.dao.ProdutoDAO;
import br.com.casadocodigo.loja.models.RelatorioProdutos;

@Controller
public class RelatorioProdutosController {

	@Autowired
	ProdutoDAO produtoDao;

	@RequestMapping(value = "/relatorio-produtos", method = RequestMethod.GET)
	@ResponseBody
	public RelatorioProdutos relatorioJSON(@RequestParam(value = "data", required = false) String dataString) throws ParseException {
		
		RelatorioProdutos relatorio = new RelatorioProdutos();		
		relatorio.setDataGeracao(Calendar.getInstance().getTime());
				
		if (dataString == null) {
			relatorio.setProdutos(produtoDao.listar());
		} else {

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Calendar data = Calendar.getInstance();
			Date date = sdf.parse(dataString);
			data.setTime(date);

			relatorio.setProdutos(produtoDao.listar(data));
		}
		
		relatorio.setQuantidade(relatorio.getProdutos().size());
				
		return relatorio;
	}
}
