package org.liuyk.desgin.controller;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.liuyk.desgin.model.DBConnInfo;
import org.liuyk.desgin.model.TableInfo;
import org.liuyk.desgin.service.IDBService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Controller
@RequestMapping("/db")
@SessionAttributes("dBConnInfo")
public class DBController {

	@Inject
	private IDBService dbService;
	
	@RequestMapping(value="/configConn", method=RequestMethod.GET)
	public String configConn() {
		return "configConn";
	}
	
	@RequestMapping(value="/configConn", method=RequestMethod.POST)
	public String configConn(String host, int port, String username, String password, Model model) {
		DBConnInfo connInfo = new DBConnInfo(host, port, username, password, null);
		List<String> databases = dbService.showDatabases(connInfo);
		model.addAttribute("databases", databases);
		model.addAttribute("dBConnInfo", connInfo);
		return "databases";
	}
	
	@RequestMapping("/{d}/showtables")
	public String showTableInfos(@PathVariable("d") String schema, HttpSession session, Model model) {
		DBConnInfo connInfo = (DBConnInfo) session.getAttribute("dBConnInfo");
		if(connInfo == null) {
			return InternalResourceViewResolver.REDIRECT_URL_PREFIX + "/db/configConn";
		}
		List<String> tableNames = dbService.showTableNames(connInfo, schema);
		List<TableInfo> tableInfos = new ArrayList<TableInfo>();
		for (String tname : tableNames) {
			TableInfo tableInfo = dbService.showTable(connInfo, schema, tname);
			tableInfos.add(tableInfo);
		}
		model.addAttribute("tableInfos", tableInfos);
		model.addAttribute("schema", schema);
		return "tables";
	}
	
}
