package com.wealth_wise.controller;

import com.wealth_wise.DTO.ExpenseDTO;
import com.wealth_wise.DTO.IncomeDTO;
import com.wealth_wise.service.ExcelService;
import com.wealth_wise.service.ExpenseService;
import com.wealth_wise.service.IncomeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/excel")
@RequiredArgsConstructor
public class ExcelController {

    private final ExcelService excelService;
    private final IncomeService incomeService;
    private final ExpenseService expenseService;

    @GetMapping("/download/income")
    public void downloadIncomeExcel(HttpServletResponse response) throws IOException {
        List<IncomeDTO> incomes = incomeService.getCurrentMonthIncomesForCurrentUser();
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=income.xlsx");
        excelService.writeIncomesToExcel(response.getOutputStream(), incomes);
    }

    @GetMapping("/download/expense")
    public void downloadExpenseExcel(HttpServletResponse response) throws IOException {
        List<ExpenseDTO> expenses = expenseService.getCurrentMonthExpensesForCurrentUser();
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=expense.xlsx");
        excelService.writeExpensesToExcel(response.getOutputStream(), expenses);
    }
}
