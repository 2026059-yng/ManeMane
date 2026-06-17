package com.example.qa_app.service;

import java.util.List;
import org.springframework.stereotype.Service;

import com.example.qa_app.repository.CashFlowRepository;
@Service
public class CashFlowService {
    private final CashFlowRepository cashFlowRepository;
    
    public CashFlowService(CashFlowRepository cashFlowRepository){
        this.cashFlowRepository=cashFlowRepository;
    }

    public int calcExpenseThisMonth(int user_id){
        List<Daily>expenses=new ArrayList<Daily>();
        int sum = 0;
        for(int i = 0;i<expenses.size();i++){
            sum+=expenses.get(i).getDailyAmount();

        }
        return sum;
        
    }

    
}


// package com.example.bookstore;

// import java.util.List;
// import org.springframework.stereotype.Service;

// @Service
// public class BookService {
//   private final BookRepository bookRepository;

//   public BookService(BookRepository bookRepository) {
//     this.bookRepository = bookRepository;
//   }

//   public List<Book> findAll() {
//     return bookRepository.findAll();
//   }

//   public void register(BookForm form) {
//     // ビジネスルール 1: 価格は 100 以上
//     if (form.getPrice() < 100) {
//       throw new IllegalArgumentException("価格は 100 以上で指定してください");
//     }
//     // ビジネスルール 2: 同じタイトルは登録しない
//     if (bookRepository.existsByTitle(form.getTitle())) {
//       throw new IllegalStateException("同じタイトルの本がすでに登録されています");
//     }
//     bookRepository.save(new Book(form.getTitle(), form.getPrice(), 0));
//   }
// }