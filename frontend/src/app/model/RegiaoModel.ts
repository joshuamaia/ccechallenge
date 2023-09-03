import { ICompra } from "./CompraModel";
import { IGeracao } from "./GeracaoModel";
import { IPrecoMedio } from "./PrecoMedioModel";

export interface IRegiao {
  sigla: String;
  geracao: IGeracao;
  compra: ICompra;
  precoMedio: IPrecoMedio
}
