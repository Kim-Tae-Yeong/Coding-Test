아래와 같이 bfs 실행 시 시간 초과

    while(len(q) != 0):
    
        node = q.popleft()
    
        visited[node] = True
    
        for elem in graph[node]:
    
          if(not visited[elem]):
      
            q.append(elem)

for elem in graph[node] 반복문에서 방문하지 않는 노드를 True로 만들면 이후 다른 노드에서 해당 노드를 탐색할 때 큐에 넣지 않아도 됨
