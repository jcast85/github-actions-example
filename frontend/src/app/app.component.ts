import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { importProvidersFrom } from '@angular/core';

interface Company {
  id?: number;
  name: string;
  address: string;
}

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [CommonModule, FormsModule, HttpClientModule],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})
export class AppComponent implements OnInit {
  title = 'my-first-project';
  companies: Company[] = [];
  newCompany: Company = { name: '', address: '' };
  selectedCompany: Company | null = null;
  private apiUrl = 'http://localhost:8080/api/companies';

  constructor(private http: HttpClient) {}

  ngOnInit() {
    this.loadCompanies();
  }

  loadCompanies() {
    this.http.get<Company[]>(this.apiUrl)
      .subscribe(data => this.companies = data);
  }

  addCompany() {
    this.http.post<Company>(this.apiUrl, this.newCompany)
      .subscribe(() => {
        this.newCompany = { name: '', address: '' };
        this.loadCompanies();
      });
  }

  editCompany(company: Company) {
    this.selectedCompany = { ...company };
  }

  updateCompany() {
    if (!this.selectedCompany || !this.selectedCompany.id) return;

    this.http.put(`${this.apiUrl}/${this.selectedCompany.id}`, this.selectedCompany)
      .subscribe(() => {
        this.selectedCompany = null;
        this.loadCompanies();
      });
  }

  deleteCompany(id: number) {
    this.http.delete(`${this.apiUrl}/${id}`)
      .subscribe(() => this.loadCompanies());
  }

  cancelEdit() {
    this.selectedCompany = null;
  }
}
