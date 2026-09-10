import java.util.Iterator;

public class MiLista implements ListInterface{
    ListNode cabeza;

    @Override
    public boolean isEmpty() {
        return this.cabeza == null;
    }

    @Override
    public int getSize() {
        ListNode iterador = this.cabeza;
        int contador = 1;
        while (iterador.siguiente != null){
            iterador = iterador.siguiente;
            contador = contador + 1;
        }
        return contador;
    }

    @Override
    public void clear() {
        this.cabeza = null;

    }

    @Override
    public Object getHead() {
        if (this.cabeza == null) {
            return null;
        }
        return this.cabeza.dato;
    }

    @Override
    public Object getTail() {
        if (this.cabeza == null) {
            return null;
        }
        ListNode iterador = this.cabeza;
        while (iterador.siguiente != null) {
            iterador = iterador.siguiente;
        }
        return iterador.dato;
    }

    @Override
    public Object get(ListNode node) {
        if (node == null) {
            return null;
        }
        return node.dato;
    }

    @Override
    public Object search(Object object) {
        ListNode iterador = this.cabeza;
        while (iterador != null) {
            if (iterador.dato == null ? object == null : iterador.dato.equals(object)) {
                return iterador;
            }
            iterador = iterador.siguiente;
        }
        return null;
    }

    @Override
    public boolean add(Object object) {
        return insertTail(object);
    }

    @Override
    public boolean insert(ListNode node, Object object) {
        try {
            if (node == null) {
                return insertHead(object);
            }
            ListNode nuevoNodo = new ListNode(object);
            nuevoNodo.siguiente = node.siguiente;
            node.siguiente = nuevoNodo;
            return true;
        } catch (Exception e) {
            System.out.println("Ocurrió un error");
            return false;
        }
    }

    @Override
    public boolean insert(Object ob, Object object) {
        try {
            ListNode nodoReferencia = (ListNode) search(ob);
            if (nodoReferencia == null) {
                return false;
            }
            return insert(nodoReferencia, object);
        } catch (Exception e) {
            System.out.println("Ocurrió un error");
            return false;
        }
    }

    @Override
    public boolean insertHead(Object object) {
        try {
            // 1er paso: Crear el nuevo nodo con la información recibida
            ListNode nuevaCabeza = new ListNode(object);
            //2do paso: Conectar el nodo a la cabeza
            nuevaCabeza.siguiente = this.cabeza;
            //3er paso: redefinir la cabeza
            this.cabeza = nuevaCabeza;
            return true;
        } catch (Exception e){
            System.out.println("Ocurrió un error");
            return false;
        }
    }

    @Override
    public boolean insertTail(Object object) {
        if(this.cabeza == null){
            ListNode nuevaCabeza = new ListNode(object);
            this.cabeza = nuevaCabeza;
        }else {
            ListNode nuevaCola = new ListNode(object);
            ListNode iterador = this.cabeza;
            while (iterador.siguiente != null) {
                iterador = iterador.siguiente;

            }
            iterador.siguiente = nuevaCola;
        }
        return true;

    }

    @Override
    public boolean set(ListNode node, Object object) {
        if (node == null) {
            return false;
        }
        node.dato = object;
        return true;
    }

    @Override
    public boolean remove(ListNode node) {
        if (node == null || this.cabeza == null) {
            return false;
        }
        if (this.cabeza == node) {
            this.cabeza = this.cabeza.siguiente;
            return true;
        }
        ListNode iterador = this.cabeza;
        while (iterador.siguiente != null) {
            if (iterador.siguiente == node) {
                iterador.siguiente = node.siguiente;
                return true;
            }
            iterador = iterador.siguiente;
        }
        return false;
    }

    @Override
    public boolean contains(Object object) {
        return search(object) != null;
    }

    @Override
    public Iterator<ListNode> iterator() {
        return new Iterator<ListNode>() {
            private ListNode actual = cabeza;

            @Override
            public boolean hasNext() {
                return actual != null;
            }

            @Override
            public ListNode next() {
                if (!hasNext()) {
                    throw new RuntimeException("No hay más elementos en la lista");
                }
                ListNode nodo = actual;
                actual = actual.siguiente;
                return nodo;
            }
        };
    }

    @Override
    public Object[] toArray() {
        if (this.cabeza == null) {
            return new Object[0];
        }
        Object[] arreglo = new Object[getSize()];
        ListNode iterador = this.cabeza;
        int i = 0;
        while (iterador != null) {
            arreglo[i] = iterador.dato;
            i++;
            iterador = iterador.siguiente;
        }
        return arreglo;
    }

    @Override
    public Object[] toArray(Object[] object) {
        int tam = this.cabeza == null ? 0 : getSize();
        Object[] arreglo = (object.length >= tam) ? object : new Object[tam];
        ListNode iterador = this.cabeza;
        int i = 0;
        while (iterador != null) {
            arreglo[i] = iterador.dato;
            i++;
            iterador = iterador.siguiente;
        }
        if (arreglo.length > tam) {
            arreglo[tam] = null;
        }
        return arreglo;
    }

    @Override
    public Object getBeforeTo() {
        // Se asume que retorna el penúltimo nodo (el anterior al último)
        if (this.cabeza == null || this.cabeza.siguiente == null) {
            return null;
        }
        ListNode iterador = this.cabeza;
        while (iterador.siguiente.siguiente != null) {
            iterador = iterador.siguiente;
        }
        return iterador;
    }
//xd
    @Override
    public Object getBeforeTo(ListNode node) {
        if (node == null || this.cabeza == null || this.cabeza == node) {
            return null;
        }
        ListNode iterador = this.cabeza;
        while (iterador.siguiente != null) {
            if (iterador.siguiente == node) {
                return iterador;
            }
            iterador = iterador.siguiente;
        }
        return null;
    }

    @Override
    public Object getNextTo() {
        // Se asume que retorna el segundo nodo (el siguiente a la cabeza)
        if (this.cabeza == null) {
            return null;
        }
        return this.cabeza.siguiente;
    }

    @Override
    public Object getNextTo(ListNode node) {
        if (node == null) {
            return null;
        }
        return node.siguiente;
    }

    @Override
    public MiLista subList(ListNode from, ListNode to) {
        if (from == null || to == null) {
            return null;
        }
        MiLista nueva = new MiLista();
        ListNode iterador = from;
        boolean encontroFin = false;
        while (iterador != null) {
            nueva.insertTail(iterador.dato);
            if (iterador == to) {
                encontroFin = true;
                break;
            }
            iterador = iterador.siguiente;
        }
        if (!encontroFin) {
            return null;
        }
        return nueva;
    }

    @Override
    public MiLista sortList() {
        Object[] arreglo = toArray();
        for (int i = 1; i < arreglo.length; i++) {
            Object actual = arreglo[i];
            int j = i - 1;
            while (j >= 0 && ((Comparable) arreglo[j]).compareTo(actual) > 0) {
                arreglo[j + 1] = arreglo[j];
                j--;
            }
            arreglo[j + 1] = actual;
        }
        MiLista ordenada = new MiLista();
        for (Object o : arreglo) {
            ordenada.insertTail(o);
        }
        return ordenada;
    }

    @Override
    public String toString() {
        return "MiLista{" +
                "cabeza=" + cabeza +
                '}';
    }
}
