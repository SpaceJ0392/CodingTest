import copy

def crash(now, event_set):
    crash_cnt = 0
    event_dict = dict()
    
    for y, x in now.values():
        if (y, x) in event_dict : event_dict[(y, x)] += 1
        else : event_dict[(y, x)] = 1
            
    for key, val in event_dict.items():
        if val > 1 : 
            crash_cnt += 1
            event_set.add(key)

    return crash_cnt

def solution(points, routes):
    
    now = {idx : copy.deepcopy(points[route[0] - 1])\
            for idx, route in enumerate(routes)}
    end = {idx : copy.deepcopy(points[route[-1] - 1])\
            for idx, route in enumerate(routes)}
    
    robots_cnt, routes_cnt = len(routes), len(routes[0])
    event_cnt, wp = 0, 1

    event_set = set()
    waypoint = dict()
    if routes_cnt > 2:
        waypoint = {idx : copy.deepcopy(points[route[wp] - 1]) \
                    for idx, route in enumerate(routes)}
    else: waypoint = end

    while now != end:
        
        # 중계 지점 있는지 확인
        if now == waypoint: 
            wp += 1
            waypoint = {idx : copy.deepcopy(points[route[wp] - 1]) \
                        for idx, route in enumerate(routes)}
        
        # 충돌 확인 (몇 개?)
        event_cnt += crash(now, event_set)
        print(now, event_cnt)
        
        for idx in range(robots_cnt):
            # y 값 이동
            if now[idx][0] < waypoint[idx][0]: 
                now[idx][0] += 1
                continue
            if now[idx][0] > waypoint[idx][0]: 
                now[idx][0] -= 1
                continue
                
            # x 값 이동
            if now[idx][1] < waypoint[idx][1]: 
                now[idx][1] += 1
                continue
            if now[idx][1] > waypoint[idx][1]: 
                now[idx][1] -= 1
                continue
    
    # 충돌 확인 (마지막)
    event_cnt += crash(end, event_set)
    print(event_cnt)
    return event_cnt

print(solution([[2, 2], [2, 3], [2, 7], [6, 6], [5, 2]], [[2, 3, 4, 5], [1, 3, 4, 5]])) # 0
print("-" * 3)
print(solution([[3, 2], [6, 4], [4, 7], [1, 4]], [[4, 2], [1, 3], [2, 4]])) # 1
print("-" * 3)
print(solution([[3, 2], [6, 4], [4, 7], [1, 4]], [[4, 2], [1, 3], [4, 2], [4, 3]])) # 9