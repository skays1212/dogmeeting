package com.example.dogmeeting.service;

import com.example.dogmeeting.dto.RegionData;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RegionService {

    private final ObjectMapper objectMapper;
    
    // JSON 데이터를 문자열로 저장
    private static final String REGION_JSON = """
        [
          {
            "city": "서울특별시",
            "districts": [
              "종로구", "중구", "용산구", "성동구", "광진구", "동대문구", "중랑구",
              "성북구", "강북구", "도봉구", "노원구", "은평구", "서대문구", "마포구",
              "양천구", "강서구", "구로구", "금천구", "영등포구", "동작구", "관악구",
              "서초구", "강남구", "송파구", "강동구"
            ]
          },
          {
            "city": "부산광역시",
            "districts": [
              "중구", "서구", "동구", "영도구", "부산진구", "동래구", "남구", "북구",
              "해운대구", "사하구", "금정구", "강서구", "연제구", "수영구", "사상구", "기장군"
            ]
          },
          {
            "city": "대구광역시",
            "districts": [
              "중구", "동구", "서구", "남구", "북구", "수성구", "달서구", "달성군"
            ]
          },
          {
            "city": "인천광역시",
            "districts": [
              "중구", "동구", "미추홀구", "연수구", "남동구", "부평구", "계양구", "서구", "강화군", "옹진군"
            ]
          },
          {
            "city": "광주광역시",
            "districts": [
              "동구", "서구", "남구", "북구", "광산구"
            ]
          },
          {
            "city": "대전광역시",
            "districts": [
              "동구", "중구", "서구", "유성구", "대덕구"
            ]
          },
          {
            "city": "울산광역시",
            "districts": [
              "중구", "남구", "동구", "북구", "울주군"
            ]
          },
          {
            "city": "세종특별자치시",
            "districts": []
          },
          {
            "city": "경기도",
            "districts": [
              "수원시", "성남시", "의정부시", "안양시", "부천시", "광명시", "평택시", "동두천시",
              "안산시", "고양시", "과천시", "구리시", "남양주시", "오산시", "시흥시", "군포시", "의왕시",
              "하남시", "용인시", "파주시", "이천시", "안성시", "김포시", "화성시", "여주시", "광주시",
              "포천시", "연천군", "가평군", "양평군"
            ]
          },
          {
            "city": "강원특별자치도",
            "districts": [
              "춘천시", "원주시", "강릉시", "동해시", "태백시", "속초시", "삼척시",
              "홍천군", "횡성군", "영월군", "평창군", "정선군", "철원군", "화천군",
              "양구군", "인제군", "고성군", "양양군"
            ]
          },
          {
            "city": "충청북도",
            "districts": [
              "청주시", "충주시", "제천시",
              "보은군", "옥천군", "영동군", "증평군",
              "진천군", "괴산군", "음성군", "단양군"
            ]
          },
          {
            "city": "충청남도",
            "districts": [
              "천안시", "공주시", "보령시", "아산시", "서산시", "논산시", "계룡시", "당진시",
              "금산군", "부여군", "서천군", "청양군", "홍성군", "예산군", "태안군"
            ]
          },
          {
            "city": "전라북도",
            "districts": [
              "전주시", "군산시", "익산시", "정읍시", "남원시", "김제시", "완주군",
              "진안군", "무주군", "장수군", "임실군", "순창군", "고창군", "부안군"
            ]
          },
          {
            "city": "전라남도",
            "districts": [
              "목포시", "여수시", "순천시", "나주시", "광양시",
              "담양군", "곡성군", "구례군", "고흥군", "보성군", "화순군",
              "장흥군", "강진군", "해남군", "영암군", "무안군", "함평군",
              "영광군", "장성군", "완도군", "진도군", "신안군"
            ]
          },
          {
            "city": "경상북도",
            "districts": [
              "포항시", "경주시", "김천시", "안동시", "구미시",
              "영주시", "영천시", "상주시", "문경시", "경산시",
              "군위군", "의성군", "청송군", "영양군", "영덕군", "청도군",
              "고령군", "성주군", "칠곡군", "예천군", "봉화군", "울진군", "울릉군"
            ]
          },
          {
            "city": "경상남도",
            "districts": [
              "창원시", "진주시", "통영시", "사천시", "김해시", "밀양시", "거제시", "양산시",
              "의령군", "함안군", "창녕군", "고성군", "남해군", "하동군", "산청군", "함양군",
              "거창군", "합천군"
            ]
          },
          {
            "city": "제주특별자치도",
            "districts": [
              "제주시", "서귀포시"
            ]
          }
        ]
        """;

    /**
     * 모든 시/도 목록을 반환
     */
    public List<String> getCities() {
        try {
            List<RegionData> regions = objectMapper.readValue(REGION_JSON, new TypeReference<List<RegionData>>() {});
            List<String> cities = new ArrayList<>();
            for (RegionData region : regions) {
                cities.add(region.getCity());
            }
            return cities;
        } catch (Exception e) {
            throw new RuntimeException("지역 데이터를 불러오는데 실패했습니다.", e);
        }
    }

    /**
     * 특정 시/도의 구/군 목록을 반환
     */
    public List<String> getDistrictsByCity(String cityName) {
        try {
            List<RegionData> regions = objectMapper.readValue(REGION_JSON, new TypeReference<List<RegionData>>() {});
            for (RegionData region : regions) {
                if (region.getCity().equals(cityName)) {
                    return region.getDistricts();
                }
            }
            return new ArrayList<>(); // 해당 시/도를 찾지 못한 경우 빈 리스트 반환
        } catch (Exception e) {
            throw new RuntimeException("지역 데이터를 불러오는데 실패했습니다.", e);
        }
    }

    /**
     * 모든 지역 데이터를 반환
     */
    public List<RegionData> getAllRegions() {
        try {
            return objectMapper.readValue(REGION_JSON, new TypeReference<List<RegionData>>() {});
        } catch (Exception e) {
            throw new RuntimeException("지역 데이터를 불러오는데 실패했습니다.", e);
        }
    }

    /**
     * 시/도와 구/군을 조합하여 전체 주소 문자열 생성
     */
    public String getFullAddress(String city, String district) {
        if (district == null || district.trim().isEmpty()) {
            return city;
        }
        return city + " " + district;
    }
} 