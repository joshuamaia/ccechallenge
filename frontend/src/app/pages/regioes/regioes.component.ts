import { Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { ConsolidadoRegiaoDto } from 'src/app/model/ConsolidadoRegiaoDto';
import { IRegiao } from 'src/app/model/RegiaoModel';
import { RegioesService } from 'src/app/services/regioes/regioes.service';

@Component({
  selector: 'app-regioes',
  templateUrl: './regioes.component.html',
  styleUrls: ['./regioes.component.css'],
})
export class RegioesComponent implements OnInit {
  @ViewChild('paginator') paginator!: MatPaginator;
  regioes: IRegiao[];
  consolidadoRegiao: ConsolidadoRegiaoDto[] = [];
  pesquisaRegiao: String = 'NE';
  listaRegioes = ['SE', 'S', 'NE', 'N'];
  dataSource: any;
  dataSourceConsolidadp: any;
  displayedColumns = ['Sigla', 'Compra', 'Geracao'];
  displayedColumnsConsolidado = ['Sigla', 'Compra', 'Geracao'];
  enviandoArquivo: boolean = false;

  ngOnInit() {
    this.regioesService.consolidadoRegiao().subscribe((response) => {
      this.consolidadoRegiao = response;
      this.dataSourceConsolidadp = new MatTableDataSource(
        this.consolidadoRegiao
      );
    });
  }

  constructor(private regioesService: RegioesService) {
    this.pesquisaRegiao = '';
    this.regioes = [];
  }

  fomatarValores(valores: number[]) {
    return valores.join(' - ');
  }

  totalValores(valores: number[]) {
    return valores.reduce((acc, valor) => (acc = acc + valor), 0);
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
