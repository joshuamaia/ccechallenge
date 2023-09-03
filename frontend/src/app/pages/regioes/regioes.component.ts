import { Component, OnChanges, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { IRegiao } from 'src/app/model/RegiaoModel';
import { RegioesService } from 'src/app/services/regioes/regioes.service';

@Component({
  selector: 'app-regioes',
  templateUrl: './regioes.component.html',
  styleUrls: ['./regioes.component.css'],
})
export class RegioesComponent {
  @ViewChild('paginator') paginator!: MatPaginator;
  regioes: IRegiao[];
  pesquisaRegiao: String = 'NE';
  listaRegioes = ['SE', 'S', 'NE', 'N'];
  dataSource: any;
  displayedColumns = ['Sigla', 'Compra', 'Geracao'];
  enviandoArquivo: boolean = false;

  // @ViewChild(MatPaginator) paginator: MatPaginator;

  constructor(private regioesService: RegioesService) {
    this.pesquisaRegiao = '';
    this.regioes = [];
  }

  fomatarValores(valores: number[]) {
    return valores.join(' - ');
  }

  buscarRegiaoBySigla(): void {
    if (this.pesquisaRegiao) {
      this.enviandoArquivo = true;
      this.regioesService
        .buscarRegiaoBySigla(this.pesquisaRegiao)
        .subscribe((response) => {
          this.enviandoArquivo = false;
          this.regioes = response;
          this.dataSource = new MatTableDataSource(this.regioes);
          this.dataSource.paginator = this.paginator;
        });
    }
  }
}
