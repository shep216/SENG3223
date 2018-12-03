using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class GoogleMaps : MonoBehaviour {
	public RawImage img;

	string url;

	public float lat;
	public float lon;

	LocationInfo li;

	public int zoom = 14;
	public int mapWidth = 640;
	public int mapHeight = 640;

	public enum mapType { roadmap, satellite, hybrid, terrain }
	public mapType mapSelected;
	public int scale;

	IEnumerator Map()
	{
		url = "https://answers.unity.com/questions/643228/double-jump-2.html";	

		WWW www = new WWW(url);
		yield return www;
		img.texture = www.texture;
		img.SetNativeSize();
	}

	// Use this for initialization
	void Start () {
		img = gameObject.GetComponent<RawImage>();
		StartCoroutine(Map());
	}
	
	// Update is called once per frame
	void Update () {
		
	}
}
